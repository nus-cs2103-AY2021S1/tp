---
layout: page
title: Pua Xuan Ming's Project Portfolio Page
---

## Project: LogOnce

LogOnce is a one-stop logistics tracker app for clerks to monitor shipping statuses of all clients and perform common 
logistics operations. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a 
Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Order
    [\#48](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/48)
  * What it does: allows the user to create an Order object, which is linked to a specific Client object (specified by its clientid).
  * Justification: This feature allows LogOnce to track Orders made by Clients. This feature is also used by the find
  * Highlights: This enhancement is linked to the 'Order' button available in the GUI, where users are able to click on the Order button to view the list of Orders, and their respectively linked Client.
  
* **New Feature**: Update-Order
    [\#82](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/82)
    [\#103](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/103)
    [\#194](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/194)
  * What it does: allows the user to update fields of a Order object, which was previously created.
  * Justification: This feature allows users that are able to type fast to quickly fix any typo errors during the creation of a Order object.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands. The implementation was also challenging as it not all fields of a Order object might need modification. Eventually, my implemented solution was to create a UpdatedOrderFields class that would contain all the new fields (might be more than 1), before passing this object to the UpdateOrderCommand object for execution.
  
* **New Feature**: Update-Client
    [\#82](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/82)
    [\#103](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/103)
    [\#194](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/194)
  * What it does: allows the user to update fields of a Client object, which was previously created.
  * Justification: This feature allows users that are able to type fast to quickly fix any typo errors during the creation of a Client object.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands. The implementation was also challenging as it not all fields of a Order object might need modification. Eventually, my implemented solution was to create a UpdatedClentsFields class that would contain all the new fields (might be more than 1), before passing this object to the UpdateClientCommand object for execution.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=xnmng)

* **Project management**:
  * Managed releases `v1.3` - `v1.4` (3 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added documentation for the features `update client` and `update order`
      [\#215](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/215)
    * Added example screenshots for examples in `update client` and `update order`
      [\#209](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/209)
  * Developer Guide:
    * Added implementation details of the `UpdateClientCommand` and `UpdateOrderCommand`

* **Community**:
  * Contributed to forum discussions (examples: [\#245](https://github.com/nus-cs2103-AY2021S1/forum/issues/245), [\#241](https://github.com/nus-cs2103-AY2021S1/forum/issues/241), [\#240](https://github.com/nus-cs2103-AY2021S1/forum/issues/240), [\#239](https://github.com/nus-cs2103-AY2021S1/forum/issues/239), [\#166](https://github.com/nus-cs2103-AY2021S1/forum/issues/166))
  * Reported bugs (examples: [\#199](https://github.com/nus-cs2103-AY2021S1/forum/issues/199), [\#91](https://github.com/nus-cs2103-AY2021S1/forum/issues/91))
