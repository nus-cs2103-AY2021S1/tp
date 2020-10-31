---
layout: page
title: Audrey's Project Portfolio Page
---

## Project: Warenager

Warenager is an **inventory application** to help tech-savvy warehouse managers to keep track of items in their warehouse.
It **optimizes management tasks** for warehouse managers including but not exhaustive of updating,
searching and ordering supplies, via Command Line Interface (CLI).

Given below are my contributions to the project.

* **New Feature**: Added the ability to update existing stocks in the inventory.
  * What it does: Allows the user to update the details of the desired stock.
  * Justification: This feature improves the user experience as user can easily edit the details of
  existing stocks. Users now do not need to remove the item and then add them back in order to
  edit the details.
  * Highlights: Multiple stocks are able to be updated in one step. User can update the name, source, low quantity
  threshold, quantity, and location details of a stock.

* **New Feature**: Added the ability to sort existing stocks in the inventory.
  * What it does: Allows the user to sort the stocks in the inventory by the field the user wants.
  * Justification: This feature improves the user experience as the user can list the existing stocks by
  the user's order preference. Sorting in alphabetical order also helps for easier viewing of stocks.
  * Highlights: User can sort by name, source, quantity, serial number, or location. For each field the user can also
  further specify whether to sort in ascending or descending order.
  
* **New Feature**: Added the ability to suggest correct commands to the user.
  * What it does: Suggests the correct command format to the user if the command user input is in the wrong format.
  * Justification: This feature improves the user experience as the user can view and type the correct command form
  easily after entering a faulty command. This makes the user more convenient as every time the user enters a wrong
  command format, the user will not need to refer to the user guide or help and instead just need to type the suggested
  message.
  * Highlights: This feature uses the minimum edit distance heuristics to calculate the closest correct command word.
  The suggestion for the fields and prefixes is generated based on the user input itself. The minimum edit distance
  heuristics is implemented using dynamic programming algorithm.

* **Git & Repository**:
  * Created the team repository by forking from `nus-cs2103-AY2021S1/tp`
  * Set up the team repository as specified by the `CS2103T` module requirements.
  * Used GitHub Projects feature to create kanban boards to track user stories.
  * Manager and maintainer of project repository.
  * Provided help to team members about Git, GitHub, and workflow.

* **Reviews & Merging**:
  * Head reviewer for Pull Requests.
  * Split review tasks to team members.
  * Ensured no pull requests was merged before thorough review was conducted.
  * Ensured every merge pass the GitHub action checks.
  * Head merger for Pull Requests.
  * Tests other team member's code and determine functional flaws if any.

* **Testing**:
  * Created unit tests for update feature (update command parser).
  * Created integration tests for update feature (update command).
  * Created unit tests for suggestion feature (suggestion command parser, suggestion util).
  * Created integration tests for suggestion feature (suggestion command).
  * Created unit tests for sort feature (sort command parser, sort util).
  * Created integration tests for sort feature (sort command).
  * Reviewed other team members' tests and ensure the tests are adequate and working.

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `update`.
    * Added documentation for the feature `sort`.
    * Added documentation for the feature `suggestion`.
    * Fix markdown formatting errors.
  * Developer Guide:
    * Added implementation details of the `update` feature.
    * Added implementation details of the `sort` feature.
    * Added implementation details of the `suggestion` feature.
