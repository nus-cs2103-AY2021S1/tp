
## Introduction

PropertyFree is adapted from AB3. It prefers leveraging on Command 
Line Interface for those who are more comfortable with typing than with Graphical User Interface (GUI).

**PropertyFree is a management system for property agents to track and manage their property listing 
details.** It allows users to easily take control of their bookkeeping matters inclusive of seller ask prices, 
bidder buy prices, key details of properties and record keeping of past properties. 

PropertyFree also provides a 
calendar for property agents to manage their schedule be it for property viewings or meeting with clients. 
It is optimized for CLI users so that the bookkeeping can be done faster by typing in commands.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the .jar file and run the file by running it via command line or by left clicking if your device is able to.


   ![Ui](images/Ui.png)
(Fig. 1: Initial display interface for PropertyFree.)


## Key Terms and Definitions

**Seller** - A seller is the owner of a property that wants to list their house for sale. It is assigned a unique Seller Id. 

**Bidder** - A bidder is a person who wishes to bid for the purchase of a certain property. It is assigned a unique Bidder Id.

**Property** - A property is a listed estate that is up for bidder to place bids on. A property can only be added
if the given Seller Id exists in the seller list. It is assigned a unique Property Id.

**Bid** - A Bid is an offer attempt by a bidder for a specific property. A bid can only be placed if the given Property Id
and Bidder Id exists in the property list and bidder list.

**Meeting** - A meeting can be of 3 types. 

 1. View - For viewing of properties.
 2. Admin - For general admin meetings.
 3. Paperwork - For signing of paperwork related to the selling and buying of the property.
 
 All types of meeting require a valid Property Id and Bidder Id for it to be added to the schedule.

💡 SELLER_ID indicates the id of the seller.

💡 BIDDER_ID indicates the id of the bidder.

💡 PROPERTY_ID indicates the id of the property listing.

💡 VENUE indicates the venue of the meeting.

💡 DATE indicates the meeting date of the meeting.

💡 PHONE_NUMBER indicates a local Singapore phone number (Maximum 10 digits including country code +65).

# Keyboard Navigation
1. When the `CommandBox` is not in focused (i.e when cursor is not blinking):

        PRESS ENTER 

2. Next Month in `Calender`:

        PRESS CONTROL
        
3. Previous Month in `Calendar`:

        PRESS ALT - Windows OS
        or
        PRESS OPTIONS - Mac OS

Note that apart from `SHIFT` and `CONTROL`, there are `Calendar` navigation commands `next` and `prev` 
(elaborated below) in the event that your computer or laptop keyboard layout does not allow you to access
`SHIFT` or `CONTROL` easily. (or if you just prefer to type more)

# Features

> **Command format**
> - Words in `UPPER_CASE` are the parameters to be supplied by the user. e.g. in `add-p n/PROPERTY_NAME`, `PROPERTY_NAME` is a parameter which can be used as `add-p n/Sunrise Residences`.
> - Items in square brackets are optional. e.g. `edit-p [n/PROPERTY_NAME]` implies that `PROPERTY_NAME` is an optional parameter.
>- Items in <> brackets are for integer numbers only. e.g <INDEX_NUMBER> can be <5>.
  
## Viewing help 

- Command: `help`
- Format: `help`  

Displays a link to this User Guide.

---

### Command Format Summary Tables

| Command Format (Bidder)  | Example |
| :--- | :--- |
| add-b `n/BIDDER_NAME` `p/PHONE_NUMBER`  | add-b n/Marcus Duigan p/12345678  |
| edit-b `<INDEX_NUMBER>` `[n/NEW_NAME]` `[p/NEW_PHONE_NUMBER]` | edit-b 1 n/Marcus Weagle Duigan p/987654321 |
| find-b `[KEYWORDS]` | find-b duigan |
| delete-b `<INDEX_NUMBER>` | delete-b 1 |
| list-b | list-b |

| Command Format (Seller)  | Example |
| :--- | :--- |
| add-s `n/SELLER_NAME` `p/PHONE_NUMBER`  | add-s n/Marcus Duigan p/12345678  |
| edit-s `<INDEX_NUMBER>` `[n/NEW_NAME]` `[p/NEW_PHONE_NUMBER]` | edit-s 1 n/Marcus Weagle Duigan p/987654321 |
| find-s `[KEYWORDS]` | find-s duigan |
| delete-s `<INDEX_NUMBER>` | delete-s 1 |
| list-s | list-s |

| Command Format (Property)  | Example |
| :--- | :--- |
| add-p `n/PROPERTY_NAME` `s/SELLER_ID` `ap/ASKING_PRICE` `t/TYPE` `a/ADDRESS` `r/IS_RENTAL`  | add-p n/Sunrise s/S1 ap/100 t/Landed a/99 Sunrise Street r/No  |
| edit-p `<INDEX_NUMBER>` `[n/NAME]` `[a/ADDRESS]` `[s/SELLER_ID]` `[ap/ASKING_PRICE]` `[t/PROPERTY_TYPE]` `[r/IS_RENTAL]`  | edit-p 1 n/Cove Residences a/23 Cove Street |
| find-p `[p/PROPERTY_ID_KEYWORDS]` `[n/NAME_KEYWORDS]` `[a/ADDRESS_KEYWORDS]` `[s/SELLER_ID_KEYWORDS]` `[t/PROPERTY_TYPE_KEYWORDS]` `[ap/ASKING_PRICE_FILTER]` `[r/IS_RENTAL]` `[c/IS_CLOSED_DEAL]` | find-p n/Sunrise Cove a/Street Road |
| delete-p `PROPERTY_ID` or delete-p `<INDEX_NUMBER>` | delete-p P23 or delete-p 5 |
| list-p | list-p |

| Command Format (Bid)  | Example |
| :--- | :--- |
| add-bid `b/PROPERTY_ID` `c/BIDDER_ID` `m/BID_AMOUNT`  | add-bid b/P1 c/B2 m/150000.20  |
| edit-bid `<INDEX_NUMBER>` `[b/NEW_PROPERTY_ID]` `[c/NEW_BIDDER_ID]` `[m/NEW_BID_AMOUNT]` | edit-bid 1 b/ P99 c/ B12 m/1.20 |
| find-bid `[KEYWORDS]` | find-bid P1 B2 $65000.00 |
| delete-bid `<INDEX_NUMBER>` | delete-bid 1 |
| list-bid | list-bid |

| | Command Format (Meeting)  | Example |
| :--- | :--- | :--- |
| View | add-m q/v `b/<BIDDER_ID>` `p/<PROPERTY_ID>` `v/<VENUE>` `d/<DATE> s/<STARTTIME> e/<ENDTIME>` | add-m q/v b/B12 p/P12 v/2 ALBERT PARK d/11-12-2021 s/12:30 e/13:00 |
| Administrative | add-m q/a `b/<BIDDER_ID>` `p/<PROPERTY_ID>` `v/<VENUE>` `d/<DATE> s/<STARTTIME> e/<ENDTIME>` | add-m q/a b/B12 p/P12 v/2 ALBERT PARK t/11-12-2021 |
| Paperwork  |add-m q/p `b/<BIDDER_ID>` `p/<PROPERTY_ID>` `v/<VENUE>` `d/<DATE> s/<STARTTIME> e/<ENDTIME>` | add-m q/p b/B12 p/P12 v/2 ALBERT PARK t/11-12-2021 |
| |edit-m `<INDEX>` `[b/BIDDER_ID]` `[p/PROPERTY_ID]` `[v/VENUE]` `[d/DATE] [s/<STARTTIME>] [e/<ENDTIME>]` | edit-m 2 v/eunos |
| |delete-m `<INDEX_NUMBER>` | delete-m 3 |
| | sort-m `o/ORDER` | sort-m o/asc |
| | list-m | list-m |

| Command Format (Calendar)  | Example |
| :--- | :--- |
| next  | next  |
| prev | prev |

# Client
Clients of PropertyFree consists of both bidders and sellers.

- ```Sellers``` are the owners of the properties listed in PropertyFree.
- ```Bidders``` are the interested buyers who place a bid on a property.

💡 Note that the ```Tag``` and ```Id``` for both ```bidder```
and ```seller``` are created automatically and ***cannot be modified***.
- ```Tag```: Visual identifier for clients
- ```Id```: Unique ID assigned to each client for identification.

💡 Note that the following applies for information a client.

1. The length of the name of any clients 
that PropertyFree accepts is of **26 characters or lesser** (including spaces).
2. The length of the phone number of any clients
that PropertyFree accepts is of **11 numbers of lesser**.

PropertyFree assumes that **all phone numbers are unique**, and as such will not allow duplicates of
phone number. 

# Bidder Features

## **Adding a Bidder**

Adds a bidder to the bidder list.

- Command: `add-b`
- Format: `add-b n/BIDDER_NAME p/PHONE_NUMBER`

Example:

```
add-b n/Marcus Duigan p/12345678
```

Expected Output:

```
New bidder added:   
Name: Marcus Duigan 
Phone number: 12345678
Id: B1
Tag: bidder
```

## Searching for **Bidder**

User can search for a list of bidders based on their name. The returned list will retain names that matched with the keywords supplied.

- Command: `find-b`
- Format: **`find-b [KEYWORDS]`**

Example:

```
find-b duigan
```

Expected Output:

```
1 bidder(s) listed!
```

## View List **of Bidder**

Brings user to the bidder tab and shows the whole list of bidders. Can be used after filtering / searching bidders.

- Command: `list-b`
- Format: `list-b`

Example:

```
list-b
```

Expected Output:

```
Listed all bidder(s).
```

## Edit **of Bidder**

Edits the information of a bidder who is corresponding to the index in the list of bidders. Names, phone number and tags can be edited. 

- Command: `edit-b`
- Format: `edit-b <INDEX_NUMBER> [n/NEW_NAME] [p/NEW_PHONE_NUMER]`


> - Edits the bidder at the specified ```INDEX_NUMBER```, which refers to the index shown on the displayed bidder list. The index must be a **positive integer** 1, 2, 3...
> - At least one optional field must be provided.
> - Existing values will be updated to the input values.
> - All other values will remain the same.

Example:
```
edit-b 1 n/Marcus Weagle Duigan p/987654321
```

Expected Output:

```
Edited Bidder:
Name: Marcus Weagle Duigan
Phone: 987654321
Id: B1
Tag: bidder
```

## Delete **Bidder**

Deletes the bidder that is corresponding to the index of the bidder in the list of the bidders.

- Command: `delete-b`
- Format: `delete-b <INDEX_NUMBER>`

> - Deletes the bidder at the specified ```INDEX_NUMBER```, which refers to the index shown on the displayed bidder list. The index must be a **positive integer** 1, 2, 3...


Example:

```
delete-b 1 
```

Expected Output:

```
Deleted Bidder:
Name: Marcus Weagle Duigan
Phone: 987654321
Id: B1
Tag: bidder
All related bids and meetings have been deleted.
```

# Seller Features

## **Addition of Seller**

Adds a seller to the seller list.

- Command: `add-s`
- Format: `add-s n/SELLER_NAME p/PHONE_NUMBER`

Example:

```
add-s n/Kor Ming Soon p/12345778 
```

Expected Output:

```
New seller added:
Name: Kor Ming Soon
Phone number: 12345778 
Id: S1
Tag: seller
```

## Searching for **Seller**

User can search for a list of sellers based on their name. The returned list will retain names that matched with the keywords supplied.

- Command: `find-s`
- Format: **`find-s [KEYWORDS]`**

Example:

```
find-s Ming
```

Expected Output:

```
1 seller(s) listed!
```

## View List **of Seller**

Brings user to the seller tab and shows the whole list of sellers. Can be used after filtering / searching sellers.

- Command: `list-s`
- Format: `list-s`

Example:

```
list-s
```

Expected Output:

```
Listed all seller(s).
```

## Edit **of Seller**

Edits the information of a seller who is corresponding to the index in the list of sellers. Names, phone number and tags can be edited. 

- Command: `edit-s`
- Format: `edit-s <INDEX_NUMBER> [n/NEW_NAME] [p/NEW_PHONE_NUMER]`

> - Edits the seller at the specified ```INDEX_NUMBER```, which refers to the index shown on the displayed seller list. The index must be a **positive integer** 1, 2, 3...


Example:

```
edit-s 1 n/Joven Kor Ming Soon p/987654321
```

Expected Output:

```
Edited Seller:
Name: Joven Kor Ming Soon
Phone: 987654321
Id: S1
Tag: seller
```

## Delete **Seller**

- Command: `delete-s`
- Format: `delete-s <INDEX_NUMBER>`

> - Deletes the seller at the specified ```INDEX_NUMBER```, which refers to the index shown on the displayed seller list. The index must be a **positive integer** 1, 2, 3...

Example:

```
delete-s 1 
```

Expected Output:

```
Deleted Seller:
Name: Joven Kor Ming Soon
Phone: 987654321
Id: B1
Tag: seller
All related properties and meetings have been deleted.
```

# Property Features

## Adding a property

Adds a property and its relevant details to the property list.

- Command:  `add-p`
- Format: `add-p n/PROPERTY_NAME s/SELLER_ID ap/ASKING_PRICE t/TYPE a/ADDRESS r/IS_RENTAL`

💡 ```IS_RENTAL``` can be one of the following formats: Yes / yes / Y / y or No / no / N / n  
💡 Property name refers to the generic brand name and can be the same for multiple properties, whereas address refers to the full address of the property and must be unique.  

⚠️ : The seller id must exist inside the seller list.  
⚠️ : Price must be greater than 0 and less or equals to 1 trillion.

Example:

```
add-p n/Sunrise s/S1 ap/100 t/Landed a/99 Sunrise Street r/No
```

Expected Output:

```
New property added: Sunrise
Property Id: P1
Address: 99 Sunrise Street
Property type: Landed
Asking price: $100.00
Seller Id: S1
```

ℹ️ As seen in the example, a unique unmodifiable property id is automatically generated for every new property.  

## Listing all properties

Brings user to the property tab and shows the whole list of properties. Can be used after filtering / searching properties.

- Command: `list-p`
- Format: `list-p`

Example:
```
list-p
```

Expected Output:

```
Listed all properties.
```

## Editing a property

Edits an existing property in the property list.  

- Command: `edit-p`
- Format: `edit-p <INDEX_NUMBER> [n/NAME] [a/ADDRESS] [s/SELLER_ID] [ap/ASKING_PRICE] [t/PROPERTY_TYPE] [r/IS_RENTAL]`

> - Edits the property at the specified `INDEX_NUMBER`, which refers to the index shown on the displayed property list. The index must be a **positive integer** 1, 2, 3...
> - At least one optional field must be provided.
> - Edits the property at the specified ```INDEX_NUMBER```, which refers to the index shown on the displayed property list. The index must be a **positive integer** 1, 2, 3...

💡 Note that the property id cannot be modified.  

Example:

```
edit-p 1 n/Cove Residences a/23 Cove Street
```
Edits the property name and address of the first property to `Cove Residences` and `23 Cove Street` respectively.

Expected output:
```
Edited Property: Cove Residences
Property id: P4
Address: 23 Cove Street
Property type: HDB
Asking price: $99.00
Seller id: S20
```

## Finding properties

Find properties that satisfy all of the user's filters.  
Format: `find-p [p/PROPERTY_ID_KEYWORDS] [n/NAME_KEYWORDS] [a/ADDRESS_KEYWORDS] [s/SELLER_ID_KEYWORDS] [t/PROPERTY_TYPE_KEYWORDS] [ap/ASKING_PRICE_FILTER] [r/IS_RENTAL] [c/IS_CLOSED_DEAL]`

|   |**Formats**|
|---|---|
|💡|Format for attributes that search by keywords (property name, address, seller id, property id, property type): keywords delimited by whitespace. <br>For example, `n/Sunrise Cove a/Street Road`<br><br> Format for asking price filter: `< / <= / == / > / >= PRICE`<br>For example, `ap/<= 100`<br><br>Format for is rental: `y / yes / n / no`<br><br>Format for is closed deal: `close` or `active`|

> - The search is case insensitive for all attributes. e.g. `cove` will match `Cove`.
> - For keywords-based search, the order of keywords does not matter. e.g. `Street Main` will match with `Main Street`.
> - Only full words will be matched. e.g. `Sun` will not match `Sunrise`.
> - Properties matching at least one keyword is considered a match. e.g. `Sunrise View` will match `Sunrise Avenue`.
> - The search returns properties matching **all** options. e.g. The command `find-p n/Sunrise ap/< 100` will return the property with name `Sunrise Avenue` and asking price `99` but not the property with name `Sunrise Avenue` with asking price of `500`.

Example:

```
find-p n/Cove Sunrise ap/<= 100 r/no
```

Expected output:
```
2 properties listed!
```
Displays all properties whose names contains either `Cove` or `Sunrise`, asking price is less than or equals to `100`, **and** is not a rental property.

## Deleting a property

Deletes a property listing from the property list.  

💡 Automatically deletes all bids and meetings associated with the property being deleted.

- Command: `delete-p`
- Format: `delete-p PROPERTY_ID` or `delete-p <INDEX_NUMBER>`

> - Deletes the property with the specified `PROPERTY_ID`.
> - Deletes the property at the specified ```INDEX_NUMBER```, which refers to the index shown on the displayed property list. The index must be a **positive integer** 1, 2, 3...


Examples:

```
delete-p P23
```
Deletes the property whose property id is `P23`.

```
delete-p 5
```
Deletes the fifth property in the property list.

Expected Output:
```
Deleted Property: Sunrise Avenue
Property id: P23
Address: Block 123
Property type: HDB
Asking price: $100.00
Seller id: S2
All related bids and 
meetings have been 
deleted.
```

---

# Bid Features

---

## Adding a Bid for a Property

Adds a bid to the bid list. The Bid is automatically sorted by property Id followed by the bid amount.

- Command: `add-bid`
- Format: `add-bid b/PROPERTY_ID c/BIDDER_ID m/BID_AMOUNT`

> ⚠️: The ```BIDDER_ID``` and ```PROPERTY_ID``` must exist to be added successfully.

Example:

```
add-bid b/P1 c/B2 m/150000.20
```

Expected Output:

```
New bid added: 
Bid of $150000.20
by B2
to property: P1
```

## View Full List of Bids

Will display all bids in the bid list

- Command: `list-bid`
- Format: `list-bid`

Example:

```
list-bid
```

Expected Output:

```
Listed all bids
```

## Deletion of Existing Bid

Will delete a bid based on its number in the bid list

- Command: `delete-bid`
- Format: `delete-bid <INDEX_NUMBER>`

> - Deletes the bid at the specified ```INDEX_NUMBER```, which refers to the index shown on the displayed bid list. The index must be a **positive integer** 1, 2, 3...

Example:

```
delete-bid 1
```

Expected Output:

```
Deleted Bid: 
Bid of $150000.20
by B2
to property: P1
```

## Editing a Bid

Will edit a bid based on the values given for each parameter.  The edited Bid is automatically sorted by property Id followed by the bid amount.

- Command: `edit-bid`
- Format: `edit-bid <INDEX_NUMBER> [b/PROPERTY_ID] [c/BIDDER_ID] [m/BID_AMOUNT]`

> - Edits the bid at the specified ```INDEX_NUMBER```, which refers to the index shown on the displayed bid list. The index must be a **positive integer** 1, 2, 3...
> - At least one optional field must be provided.
> - Existing values will be updated to the input values.
> - All other values will remain the same.  
> - ⚠️: The ```BIDDER_ID``` and ```PROPERTY_ID``` input must be in the bidder and property list to be edited successfully.   
       
Example:

```
edit-bid 1 b/ P99 c/ B12 m/1.20
```

Expected Output:

```
Edited Bid:

FROM: 
Bid of $999999.00
by B2
to property: P3 

TO: 
Bid of $1.20
by B12
to property: P99
```

## Find a specific bid based on key words

Will display all bids in the bid list that contains the keywords specified by the user

- Command: `find-bid`
- Format: `find-bid [KEYWORDS]`

Example:

```
find-bid P1 B2 $65000.00
```

Expected Output:

```
1 bid(s) listed!
```

---

# Meeting Features

---

There are three types of meetings of which the agent will be able to add:

1. View - For viewing of properties `v`
2. Admin - For general admin meetings `a`
3. Paperwork - For signing of paperwork related to the selling and buying of the house `p`

---

## Add a Meeting

Creates a meeting of the various type to be added to the schedule. To create the 3 different types of meeting we use the `q/`
attribute. 

- Command: `add-m`
- Format: `add-m q/MEETING_TYPE b/BIDDER_ID p/PROPERTY_ID v/VENUE d/DATE s/STARTTIME e/ENDTIME`

| Attributes      |Example Inputs |Remarks|
|------|------|-----| 
| Meeting Type| `v` `a` `p` | Represents the three different meetings.|
| Bidder ID| `B1` `B3` | The bidder has to exist to create the meeting. The letter "B" has to be capitalised.|
| Property ID| `P1`  `P4` | The property has to exist to create the meeting. The letter "P" has to be capitalised.|
| Venue| `Bedok Mall Starbucks` `Tampines St 3, BLK 222, #05-211`| Represents meeting venue. This does not have to be at the property itself.|
| Date| `12-12-2021` `12-08-2021`| Date has to be of the format `DD-MM-YYYY` and cannot be a past date.|
| Start Time, End Time| `16:20` `14:20`| Time has to be of the format `HH:MM`. The meetings timings are allowed to clash each other and can be past midnight.|

Example:

```
add-m q/v b/B1 p/P1 v/2 ALBERT PARK d/11-12-2021 s/12:30 e/13:00 

```

Expected Output:

```
New meeting added: 
Meeting Type: Viewing
Bidder Id: B1
Property Id: P1
Venue: 2 ALBERT PARK
Date: 11-12-2021
Start Time: 12:30
End Time: 13:00
```

## Deleting an Existing Meeting


Deletes a meeting from the calendar when a meeting is cancelled.

- Command: `delete-m`
- Format: `delete-m <INDEX_NUMBER>`


💡 The index has to be a positive number and the meeting has to exist.

> - Deletes the meeting at the specified `INDEX_NUMBER`, which refers to the index shown on the displayed meeting list. The index must be a positive integer 1, 2, 3...


Example:

```
delete-m 3
```

Expected Output:

```
Deleted Meeting: 
Meeting Type: Viewing
Bidder Id: B1
Property Id: P1
Venue: 2 ALBERT PARK
Date: 11-12-2021
Start Time: 12:30
End Time: 13:00
```

## View the list of all Meetings

Displays all the meetings in the meeting list. Can be used after using the find meeting feature.

- Command: `list-m`
- Format: `list-m`

Example:

```
list-m
```
Expected Output:

```
Listed all meetings
```

## Editing an Existing Meeting

Edits an existing meeting in the meeting list. The existing meeting to be editted will have its parameters updated with the new values provided by the user.

- Command: `edit-m`
- Format: `edit-m <INDEX_NUMBER> b/BIDDER_ID p/PROPERTY_ID v/VENUE d/DATE s/STARTTIME e/ENDTIME`
> - At least one optional field must be provided.
> - Edits the meeting at the specified `INDEX_NUMBER`, which refers to the index shown on the displayed meeting list. The index must be a positive integer 1, 2, 3...

💡 Note that the meeting type cannot be modified.  
Example:

```
edit-m 2 v/eunos
```

Expected Output:

```
Edited Meeting: Admin
Bidder Id: B12
Property Id: P12
Venue: eunos
Date: 12-05-2016
```

## Sorting the existing Meeting List


Sorts the meeting in the meeting list according to the meeting date. The sort will be either ascending or descending meeting dates.

- Command: `sort-m o/asc`
                     `sort-m o/dsc`
- Format: `sort-m o/<ORDER>`


💡 The order is either asc or dsc which will sort the meetings according to the meeting date either ascending or descending respectively.

> - Sorts the meeting in the displayed meeting list according to the meeting date.


Example:

```
sort-m o/asc
```

Expected Output:

```
Successfully sorted meeting
```

## Find an Existing Meeting

Finds an existing meeting detail that is in the list.

- Command: `find-m`
- Format: `find-m <INDEX_NUMBER> b/BIDDER_ID p/PROPERTY_ID v/VENUE d/DATE s/STARTTIME e/ENDTIME`

💡 Meetings can be found with by specifying at least one of the attributes. Not all attributes are compulsory.

💡 Attributes can be any order and inputs can be in any case (Upper and Lower).

Example:

```
find-m b/B1 v/bedok
```

Expected Output:

```
2 meeting(s) listed!
```
The index will only correspond to the original list, NOT the filtered list (when used in find).


## Calendar Navigation Features

Calendar navigation features are commands which help the agent to navigate through the calendar. There are only two commands `next` and `prev` which aim to better serve agents who are more inclined with command line interface. 

## Navigate To Next Month in Calendar

Brings the next month page to view.

- Command: `next`
- Format: `next`


Example:

```
next
```

Expected Output:

```
Display next month of Calendar
```
Picture Example:

| Before (October)      | After (November) |
| ----------- | ----------- |
| ![october](images/CalendarPictures/Calendar_October.png) | ![november](images/CalendarPictures/Calendar_November.png)|


## Navigate To Previous Month in Calendar

Brings the previous month page to view.

- Command: `prev`
- Format: `prev`

Example:

```
prev
```

Expected Output:

```
Display previous month of Calendar
```

Picture Example:

| Before (November)      | After (October) |
| ----------- | ----------- |
| ![november](images/CalendarPictures/Calendar_November.png) | ![october](images/CalendarPictures/Calendar_October.png) |

---

# Thank You For Using PropertyFree!


