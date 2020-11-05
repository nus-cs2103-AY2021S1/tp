---
layout: page
title: Wang Ri Zhao's Project Portfolio Page
---

## Project: FixMyAbs

FixMyAbs is a workout tracker that helps lazy programmers transform their rotund belly into toned six packs.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=therizhao)

* **Core Feature**: `addex` command to add new exercise
    - What: Allows user to add new exercises
    - Why: Allows user to come up with new exercises rather than the boring ones prescribed by us -> improves user satisfaction -> improves user retention 
    - PR: [\#83](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/83)

* **Refactor**: Major refactoring to keep LogBook extensible
    - What: Refactor LogBook to share state to low-level components via props drilling instead of static variables
    - Why: Keep LogBook code extensible, testable and less susceptible to bugs associated with managing a global state
    - PR: [\#81](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/81)
    
* **Fix**: Fix command suggestion list to show only matching command
    - Previous: Suggestion list shows non-matching command  
    - Current: Suggestion list shows only matching command
    - Why: Give users what they expect -> Better UX
    - PR: [\#104](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/104)

* **Design**: Design UI mockup and UX flow for FixMyAbs
    - Why: Keep our team focused in solving the most important user-facing issues. Allow team to have a common reference when implementing features.
    - [Link to full design mockup](https://whimsical.com/cs2103-wireframe-CzqUnUSZGWU5ChrwE4FVYc)

* **Project management**:
    - Plan and organise weekly meetings
    - Set weekly milestones
    - Ensure team is in sync by conducting weekly stand-ups
    - Create and assign issues 
    - Lead product ideation session to come up with FixMyAbs
    - Create master branch merge protection rules to require at least 1 review in the PR to be merged -> Ensure coding standards 
    are upheld.
  
 * **Documentation**:
    - User Guide:
        - Update exercise related use cases
        - Suggest idea of using screenshots for each function to make UserGuide easier to reference
    - Developer Guide:
        - Add implementation details of `add exercise` feature

* **Community**:
    - Uphold coding standards by taking initiative to review teammates' PRs
    - Samples: [\#37](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/37), [\#60](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/60), [\#101](https://github.com/AY2021S1-CS2103-F10-3/tp/pull/101)
    