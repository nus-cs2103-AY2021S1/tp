---
layout: page
title: EndUserPersona
---
* Table of Contents
{:toc}

# EndUserPersona

This page documents the Persona of our target audiences, and how we would like to bring value to them through
`Inventoryinator`. These personas are structured in such that we capture the target group's needs in their day-to-day lives in the before scenario
and how `Inventoryinator` can transition their lives to the after scenario.

Through this, we come up with a set of UserStories we would like to implement as features in `Inventoryinator`

## Persona
### Persona #1
#### Stephen the Hardcore Gamer (Age 21)
![progamermoves](images/persona/hardcoregamer.jpg)
#### Bio:
Stephen is a hardcore gamer who plays games 10 hours a day. He is a guild leader in the game
World of Warfare, and spends a large portion of that time book keeping and managing his accounts for his guild.
He also has to juggle helping out the new members as well as distributing resources out of his accounts. Doing
this everyday has caused some book-keeping errors in his distributions and ledgers. He wants a more intuitive
way to manage and track his resources, through the use of a command line interface to perform “macros” on these
repetitive tasks.

#### Goals:
* Manage inventories of guild members
* Achieving success in battling other guilds and defeating raid bosses

#### Frustrations:
* He finds it difficult to keep track of each member’s inventory.
* He struggles to plan the distribution of resources in his guild accurately.
* He finds it tedious to do a manual update on all these repetitive tasks.

#### Scenario 1 (Before):
Stephen wants to accurately manage his guild’s inventory and ensure accurate distribution of resources. He
spends more than 4 hours a day in Microsoft Word trying to figure out a consolidated ledger of materials in
his different accounts. He manually updates each item in his Excel sheet one by one each day taking up precious
 time away from leading raids. and tearing shit up  He also has to manually select each item to update and check
 how much crafted material he can craft using the current materials stored in the guild inventories. Doing so
 is very tedious and time consuming, and also causes him to make mistakes in his tabulation.

#### Scenario 2 (After):
Using `Inventoryinator`, he can find the accurate quantities of resources inside the inventory management,
as well as the number distributed in his guild account inventories. Then he needs to update his ledger to
consolidate the total amount of resources. He is also able to check the price of crafting goods on his game,
and simulate the number of crafted goods to make using his combined ledger to calculate potential profits.
Based on the profit shown, he can then decide if he wants to perform the crafts, and indicate to the
application to perform the craft of a certain quantity. The application will update automatically to reflect
and display his latest inventory.

### Persona #2
#### James the Casual Gamer (Age 16)
![xqcow](images/persona/casulgamer.jpg)
#### Bio:
James just started getting into games but feels very behind because many of his friends and guildmates are
 experienced in the game he plays as well as gaming in general. He is unfamiliar with the conventional game
 mechanic of crafting and often has to spend a lot of time researching online to figure out what to do. He
 spends more time reading guides, and crafting by trial and error, slowing down his progress in the game.

#### Goals:
* Manage his inventory easily.
* Have quick ways to access crafting information and recipes on the game.
* Increase his familiarity with the game’s crafting system.

#### Frustrations:
* He finds it difficult to keep track of all his inventory in the game.
* He has to spend a lot of time researching what items he is able to craft or should craft to progress in the game.

#### Scenario 1 (Before):
James wants to craft a weapon with enough attack damage in order to kill a boss for a quest.
In order to craft this weapon, he needs to craft many intermediate items. He has crafted similar weapons
before in the past but has forgotten where he needs to collect the raw materials for these intermediate items,
 and how much raw materials he will ultimately need in order to craft the weapon. He spends an hour looking up
  the game’s wiki and collating the data before he finally compiles a list of locations he should go to acquire
   the materials and calculates how much materials he will need.

#### Scenario 2 (After):
James wants to craft a weapon with enough attack damage in order to kill a boss for a quest. He
uses `Inventoryinator` to search for weapons with at least the minimum damage required and selects
one of the weapons. `Inventoryinator` shows James the total raw materials needed for the weapon based
on the recipes he had input the previous time he crafted it and places he had recorded to acquire these materials.
James goes back into his game and begins his collection.

### Persona #3
#### Serene the Hardcore Completionist (Age 25)
![catchemall](images/persona/completionist.jpg)
#### Bio:
Serene is an office lady who likes to play Build Wars 2 whenever she is off work.
 She is one of the top players in BW2 with 97.2% of the game achievements, limited only by her game time. She
  spends a long time capturing endemic life and taming pets for her last few achievements, but tends to forget those
  that she has already completed. She just wants something to keep track of what she has already done at a quick glance.

#### Goals:
* Get all the achievements.
* Easily track achievements and progress.

#### Frustrations:
* She forgets her progress on some achievements.

#### Scenario 1 (Before):
Serene has caught a new goblin and wants to move on to her next capture. However, she has to travel back to town to open
 her familiar storage to check one-by-one what she has already caught, and cross-check with the game wiki what she is
 missing. By the time she is done, an hour has passed, and she has to go to sleep or she’ll be late for work tomorrow.

#### Scenario 2 (After):
Serene opens the `Inventoryinator` and marks goblin as captured. She then filters by uncaptured, and sees kappa
next on her list. After a quick check of kappa spawns, she immediately travels to the stream and begins
looking for kappas.

### Persona #4
#### Aila the Hardcore Merchant (Age 20)
![belle](images/persona/merchant.jpg)
#### Bio:
Aila is a college student who plays Penultimate Fiction XIV. She likes to keep track of hot commodities on the
 player market, which decides her next action on which materials to bulk import from the guilds that she works with.

#### Goals:
* Make lots of money in game

#### Frustrations:
* Too many materials to keep track of
* Unable to keep track of all her transactions

#### Scenario 1 (Before):
After finishing her lectures, Aila visits the in-game market to see which items are currently selling like
hot cakes. She then runs to her warehouse to check whether she has the materials needed to craft them, and
 tallying them one-by-one takes quite a toll on her. By the time she sent out the ingredient list to her
 suppliers, it is already time for her next lecture.

#### Scenario 2 (After):
After checking the in-game market, Aila opens the `Inventoryinator`, opens up the recipe for the hot item, and
realizes she is missing 250k bear asses, 129k rat whiskers, and 2k phoenix beaks. She quickly contacts her
suppliers, then proceeds to crafting.

### Persona #5
#### Rahul the Bot Farmer (Age 35)
![hackerboi](images/persona/botfarmer.jpg)
#### Bio:
Rahul is a bot farmer, who sets up automated game accounts to farm profitable materials in the game World of Wowcraft to sell on the free market. He has over 10 accounts farming currently, and he makes a living off managing these bot accounts. He also runs similar accounts for other games like Diablow III.

#### Goals:
* Make lots of money

#### Frustrations:
* Too many materials to keep track of in over 10 accounts in a single game.

#### Scenario 1 (Before):
As a professional bot farmer, Rahul has to log into each account daily to tabulate the amount of farmed resources and
consolidate it with his main storage. He also has to check the market price of each good

#### Scenario 2 (After):
Using `Inventoryinator`, he can find the accurate quantities of resources inside the inventory management,
as well as the number distributed in his many account inventories. Then he needs to update his ledger to
consolidate the total amount of resources. He is also able to check the quantity of farmed materials on his game.
Based on the quantity shown, he can then decide if he wants to sell, and update the stock in the application.
and display his new inventory.
