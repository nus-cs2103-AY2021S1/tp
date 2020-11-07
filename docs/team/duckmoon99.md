---
layout: page
title: Wei Xin's Project Portfolio Page
---

## Project: SupperStrikers

SupperStriker is a desktop meal ordering application. The user interacts with it using a CLI, and it has a GUI created 
with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added OrderItem, Order class
  * What it does: Used to represent order items.
* **New Feature**: Added Friendly Syntax
  * What it does: Users can type prefix of a command to use it, without typing the full command. For example, users can
  type `r` instead of `remove` to use the `remove` command. If the prefix is ambiguous (match multiple commands) the
  user will be notified.
  * Justification: To reduce the number of characters needed to be typed by the user, increasing speed.
* **New Feature**: Add Undo Command
  * What it does: Add ability for user to undo last few changes to the order.
  * Justification: To allow user to recover from minor mistakes, without going through the trouble of edit or remove.
* **New Feature**: Add Price Filter
  * What it does: Users are able to list out all food item within a certain price range. For example, `price < 5` lists
  all food item with price < $5.
  * Justification: Allows price conscious users to find food within their budget.
* **New Feature**: Add Tag/Untag feature
  * What it does: Add ability for user to tag specific order item with remarks.
  * Justification: Allows users to specify special request for their food if needed, ie. "Less sugar", "No nuts"
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=duckmoon99)
* Enhancements made:
  * Wrote additional tests to increase code coverage [#228](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/228)
* **Documentation**:
    * User Guide: Update and finalised User Guide [#213](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/213)
    * Developer Guide: Explained how the Undo feature is implemented [#111](https://github.com/AY2021S1-CS2103-T16-1/tp/pull/111)
* **Team contribution**: Hosted meetings and suggested directions for the project.
