# CS2103 User Guide

**PropertyFree is a management system for property agents to track and manage their property listing details.** It **a**llows users to easily take control of their bookkeeping matters inclusive of seller ask prices, bidder buy prices, key details of properties and record keeping of past properties. PropertyFree also provides a calendar for property agents to manage their schedule be it for property viewings or meeting with clients. It is optimized for CLI users so that the bookkeeping can be done faster by typing in commands.

The following features:

- Property
- Bidder
- Seller
- Bids
- Meeting
- Calendar

[Feature List: Property ](https://www.notion.so/b94ddbd5c7244141b277c5ed2c20b5ab)

[Feature List: Bidder](https://www.notion.so/012f0ef6287647eab724b6b85adab672)

[Feature List: Seller](https://www.notion.so/c3633be0455f4216b8b9f9d6ff2a7003)

[Feature List: Bids](https://www.notion.so/00956d7c24e4414486cfe148f71f53ae)

[Feature List: Meeting](https://www.notion.so/ba8ebdb5a1844ee98c7470fd1439d60e)

[Feature List: Calendar](https://www.notion.so/fa39fa6ed7854394863dfe69cf8932cb)

# Bidder Features

## **Addition of Bidder**

Adds a bidder to the bidder list.

- Command: `add-b`
- Format: `add-b n/BIDDER_NAME p/PHONE_NUMBER`

Example:

```java
add-b n/Marcus Duigan p/12345678 
```

Expected Output:

```java
New bidder added: 
		Name: Marcus Duigan 
    Phone number: 12345678
		Id: B1
		Tags: [bidder]
```

## Searching for **Bidder**

User can search for a list of bidders based on their name. The returned list will retain names that matched with the keywords supplied.

- Command: `find-b`
- Format: **`find-b <KEYWORDS>`**

Example:

```java
find-b duigan
```

Expected Output:

```java
1 bidder(s) listed.
```

## View List **of Bidder**

Brings user to the bidder tab and shows the whole list of bidders. Can be used after filtering / searching bidders.

- Command: `list-b`
- Format: `list-b`

Example:

```java
list-b
```

Expected Output:

```java
Listed all bidders.
```

## Edit **of Bidder**

Edits the information of a bidder who is corresponding to the index in the list of bidders. Names, phone number and tags can be edited. 

- Command: `edit-b`
- Format: `edit-b <INDEX> [n/NEW_NAME] [p/NEW_PHONE_NUMER] [t/NEW_TAGS]`

Example:

```java
edit-b 1 n/Marcus Weagle Duigan p/987654321 t/Bidder
```

Expected Output:

```java
Edited Bidder:
	Name: Marcus Weagle Duigan
	Phone: 987654321
	Id: B1
	Tags: [Bidder]
```

The index will only correspond to the original list, NOT the filtered list (when used in search).

## Delete **Bidder**

Deletes the bidder that is corresponding to the index of the bidder in the list of the bidders.

- Command: `delete-b`
- Format: `delete-b <INDEX>`

Example:

```java
delete-b 1 
```

Expected Output:

```java
Deleted Bidder:
	Name: Marcus Weagle Duigan
	Phone: 987654321
	Id: B1
	Tags: [Bidder]
```

The index will only correspond to the original list, NOT the filtered list (when used in search).

# Seller Features

## **Addition of Seller**

Adds a seller to the seller list.

- Command: `add-s`
- Format: `add-s n/SELLER_NAME p/PHONE_NUMBER`

Example:

```java
add-s n/Kor Ming Soon p/12345778 
```

Expected Output:

```java
New seller added: 
		Name: Kor Ming Soon
    Phone number: 12345778 
		Id: S1
		Tags: [seller]
```

## Searching for **Seller**

User can search for a list of sellers based on their name. The returned list will retain names that matched with the keywords supplied.

- Command: `find-s`
- Format: **`find-s <KEYWORDS>`**

Example:

```java
find-s Ming
```

Expected Output:

```java
1 seller(s) listed.
```

## View List **of Seller**

Brings user to the seller tab and shows the whole list of sellers. Can be used after filtering / searching sellers.

- Command: `list-s`
- Format: `list-s`

Example:

```java
list-b
```

Expected Output:

```java
Listed all sellers.
```

## Edit **of Seller**

Edits the information of a seller who is corresponding to the index in the list of sellers. Names, phone number and tags can be edited. 

- Command: `edit-s`
- Format: `edit-s <INDEX> [n/NEW_NAME] [p/NEW_PHONE_NUMER] [t/NEW_TAGS]`

Example:

```java
edit-s 1 n/Joven Kor Ming Soon p/987654321 t/SELLER
```

Expected Output:

```java
Edited Seller:
	Name: Joven Kor Ming Soon
	Phone: 987654321
	Id: S1
	Tags: [SELLER]
```

The index will only correspond to the original list, NOT the filtered list (when used in search).

## Delete **Seller**

Deletes the seller that is corresponding to the index of the seller in the list of the sellers.

- Command: `delete-s`
- Format: `delete-s <INDEX>`

Example:

```java
delete-s 1 
```

Expected Output:

```java
Deleted Seller:
	Name: Joven Kor Ming Soon
	Phone: 987654321
	Id: B1
	Tags: [SELLER]
```

The index will only correspond to the original list, NOT the filtered list (when used in search).

## Calendar Navigation Features

Calendar navigation features are commands which help the agent to navigate through the calendar. There are only two commands `next` and `prev` which aim to better serve agents who are more inclined with command line interface. 

## Navigate To Next Month in Calendar

Brings the next month page to view.

- Command: `next`
- Format: `next`

Example:

```java
next
```

Expected Output:

```java
Display next month of Calendar
```

## Navigate To Previous Month in Calendar

Brings the next month page to view.

- Command: `prev`
- Format: `prev`

Example:

```java
prev
```

Expected Output:

```java
Display previous month of Calendar
```