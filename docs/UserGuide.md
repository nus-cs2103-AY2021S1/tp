# User Guide

Bamboo (v1.2) is a **simple desktop app for managing personal finance, optimized for use via a Command Line Interface (CLI),** and targeted at college students. If you can type fast, Bamboo v1.2 can get your financial management tasks done faster than traditional GUI apps.

---

## Table of content

1. [Quick Start](#QuickStart)
2. [Features](#Features)
3. [Usage](#Usage)
4. [Command Summary](#CommandSummary)

## Quick Start <a name="QuickStart"></a>

1. Download Java Version 11
2. Download Bamboo v1.2. &lt;Work in progress. Not available yet&gt;

## Features <a name="Features"></a>

1. **Create**
    - Adds new expense record.
    - Command: `spent`
    - [API](#spent)

2. **List**
    - Displays a list of the all the user's expenses.
    - Command: `list`
    - [API](#list)

3. **Update**
    - Edits existing expense record.
    - Command: `edit`
    - [API](#edit)

4. **Delete**
    - Deletes a specified existing expense record.
    - Command: `delete`
    - [API](#delete)

5. **Top up budget**
    - Increases budget by amount input by user .
    - Expenses are subtracted from the budget.
    - Command: `topup`
    - [API](#topup)

6. Category tagging &lt;Coming Soon v1.2.1&gt;
7. Save Load Function &lt;Coming Soon v1.2.1&gt;
8. Password &lt;Coming Soon v1.2.1&gt;
9. Help command → documentation &lt;Coming Soon v1.2.1&gt;
10. Finding and sorting (date, category, keyword, amount) &lt;Coming Soon v1.2.1&gt;
11. Multiple Accounts &lt;pending&gt;
12. GUI &lt;pending&gt;>
13. Budget notifications &lt;pending&gt;
14. Achievements &lt;pending&gt;
15. Graphs and progress trackers &lt;pending&gt;
16. Colours &lt;pending&gt;
17. Sort/Search more powerful &lt;pending&gt;
18. Customisation of workflow → shortcuts etc. &lt;pending&gt;
19. Simulation of spending &lt;pending&gt;

## Usage/ API <a name="Usage"></a>

1. **spent** <a name="spent"></a>
    - Date input (DD-MM-YYYY) is optional, defaults to system's date.
    - Order of arguments is flexible.
    - Format: `spent -d <description> $<amount_spent> [@ <date>]`
    - Example: `spent -d dinner $10.50` Adds the spending to **current date's** record
    - Example: `spent -d dinner $10.50 @ 20-08-2020` Adds the spending to **input date's** record

2. **list** <a name="list"></a>
    - Format: `list`
    - Example: `list` Displays all the items in the list.

    ![example_list](./images/ug_example/example_list.png)

    Mock-up of the list function

3. **edit** <a name="edit"></a>
    - Identified by index starting from 1.
    - Order of arguments is flexible except index.
    - **At least 1, and up to all 3**, fields (description, amount spent, date) of expense must be specified.
    - Format: `edit <index> [-d <description>] [$<amount_spent>] [@ <date>]`
    - Example: `edit 1 -d lunch $12.50`
    - Example: `edit 1 $12.50 -d lunch @ 11-11/2020`

4. **delete** <a name="delete"></a>
    - Deletes a specified existing expense record.
    - Identified by index starting from 1.
    - Format:  `delete <index>`
    - Example: `delete 1` Deletes the item at index 1 of the list.

5. **topup** <a name="topup"></a>
    - Increases budget by amount input by user .
    - Expenses are subtracted from the budget.
    - Format: `topup $<amount>`
    - Example: `topup $200` Adds an extra budget of 200 dollars to work with.

## Command summary <a name="CommandSummary"></a>

Action | Format, Examples
--------|------------------
**Spent** | `spent -d <description> $<amount_spent> [@ <date>]` <br> e.g., `spent -d dinner $10.50`, `spent -d dinner $10.50 @ 20-08-2020`
**List** | `list`
**Edit** | `edit <index> [-d <description>] [$<amount_spent>] [@ <date>]`<br> e.g.,`edit 1 -d lunch $12.50`, `edit 1 $12.50 -d lunch @ 11-11/2020`
**Delete** | `delete <index>`<br> e.g., `delete 1`
**Topup** | `topup $<amount>`<br> e.g., `topup $200`
--------------------------------------------------------------------------------------------------------------------
