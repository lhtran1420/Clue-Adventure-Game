In this project, I will create a random matrix which the length of row is range from 10 to 15 and
the length of the column ranges from 15 to 20. At the beginning, all elements in the matrix are A, 
which means available to reserve. After each API, it will update the seat and we can see which seats are
available, which seats are reserved and which seats are social-distancing. 

I will create 3 API. These are

- Get API: /v1/cinema-seat
showing all the seats in the cinema for clients, which seats are reserved(R), which seats are 
social distance(D), and which seats are available(A) to reserve. When a seat is reserved or in social
distance, you cannot reserve it for a seat. You can only reserve the available seat(A).

- Get API: /v1/cinema-operation
telling clients whether a number of seats that they want is available to reserve or not. 
For example, if they go to the cinema with n people, they will send a request with a number n. 
Therefore, this API will tell them whether these n seats are available or not. If it is available, it will suggest
them with n seats they can choose. However, if it is not enough seat for them, you have no ways to reserve
seats for n people.

- Post API: /v1/cinema-operation
supporting clients to reserve which seat that they want. In this API, clients will send a request with
a list of seats, which is the position of seat in the cinema. We will check these seats are available
to reserve or not. If it is available, we will update our cinema with these reserved seats and which 
seats are social-distancing. However, if it is not available, we will send them a message that they cannot
reserve with these seats and lead them to reserve again. 
 

