# Technical Debt

In this document we will keep track of technical debt and TODO's that popped up during development where we thought,
well that could be done better.

- [ ] Make Logger configurable: For now configuration is in a private method inside the logger, make this configurable
  from outside
- [ ] Think about access rights for Profiler getter: They don't need to be public, private could be better, because only
  start, stop and logStatistics should be used
- [ ] Build in debugging for InputManager and make it configurable
- [ ] Prepare default language files for localization manager in english, german and japanese
- [ ] Integrate the localization manager more in Infernal itself (it shouldn't be only an external API)
- [ ] It is nice to have multiple log files, but try to categorize them and then consolidate them