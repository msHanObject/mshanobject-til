Requirement
===
1. file read and write
2. function for search, rent, return
3. update present file information compare with previous file information and create, delete the file
4. check if the file exists
5. independent for layer, so it can be **object-oriented programing**
1. unit testing with jUnit


Package Explorer
--

project|package|class
---|---|---
library|entity|Book
-|-|BookCategoryList
-|-|Guest
-|-|Librarian
-|library|LibrarySystem
-|ui|UserInterface
-|-|GuestInterface:interface
-|-|LibrarianInterface:interface
-|-|BookDataFormat
-|service|LibraryService:interface
-|-|GuestService
-|-|LibrarianService
-|-|SearchService
-|-|RentalService
-|storage|BookStorage
-|-|BookStorageInterface:interface
-|-|FileStorage
-|-|FileStorageInterface:interface
