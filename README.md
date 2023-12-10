# Library
1- I've used the h2 as database and I've stored data in database
2- returns all users who have actually borrowed at least one book --> http://localhost:8080/Library/getUserAtLeastOneBorrowed
3- returns all non-terminated users who have not currently borrowed anything --> http://localhost:8080/Library/getUserWithoutBorrowed
4- returns all users who have borrowed a book on a given date --> http://localhost:8080/Library/getUserBorrowedGivenDate/2021-06-28
5- returns all books borrowed by a given user in a given date range --> http://localhost:8080/Library/getUserBorrowedGivenDateByRang/10/2000-05-14/2023-05-29
   I've mapped each user with an ID. If you want to see the result, replace these with the user's name to ID.
     Liam Aexi --> 1
     Noah Zhungwang --> 2
     Oliver Odum --> 3
     Elijah Chish --> 4
     William Jayi --> 5
     James Jumummaaq --> 6
     Sophia Oomxii -- > 7
     Charlotte Ghaada --> 8
     Ava Zhungwang --> 9
     Emma Barret-Kingsley --> 10
     Olivia Augusta --> 11 
     Poria Alizadeh --> 12
6- returns all available (not borrowed) books --> http://localhost:8080/Library/getBookNotBorrowed     
  

   
