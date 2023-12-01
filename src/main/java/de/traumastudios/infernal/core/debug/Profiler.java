package de.traumastudios.infernal.core.debug;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Profiler {
    private Map<String, Long> taskStartTime;
    private Map<String, Long> taskTotalTime;
    private Map<String, Integer> taskInvocationCount;

    public Profiler() {
        this.taskStartTime = new HashMap<>();
        this.taskTotalTime = new HashMap<>();
        this.taskInvocationCount = new HashMap<>();
    }

    public void start(String taskName) {
        this.taskStartTime.put(taskName, System.nanoTime());
    }

    public void stop(String taskName) {
        if (!this.taskStartTime.containsKey(taskName)) {
            throw new IllegalStateException("Task '" + taskName + "' not started.");
        }

        long elapsedTime = System.nanoTime() - this.taskStartTime.get(taskName);

        this.taskTotalTime.merge(taskName, elapsedTime, Long::sum);
        this.taskInvocationCount.merge(taskName, 1, Integer::sum);

        this.taskStartTime.remove(taskName);
    }

    public long getElapsedTime(String taskName) {
        return this.taskTotalTime.getOrDefault(taskName, 0L);
    }

    public int getInvocationCount(String taskName) {
        return this.taskInvocationCount.getOrDefault(taskName, 0);
    }

    public double getAverageExecutionTime(String taskName) {
        int invocationCount = getInvocationCount(taskName);
        if (invocationCount == 0) {
            return 0.0;
        }
        return (double) getElapsedTime(taskName) / invocationCount;
    }

    public void logStatistics(String taskName) {
        int invocationCount = getInvocationCount(taskName);
        long totalElapsedTime = getElapsedTime(taskName);
        double averageExecutionTime = getAverageExecutionTime(taskName);

        InfernalLogger.getInstance("profiler.log").info(String.format(
                "Task '%s' statistics: Invocations: %d, Total Time: %d nanoseconds, Average Time: %.2f nanoseconds",
                taskName, invocationCount, totalElapsedTime, averageExecutionTime));
    }
}
