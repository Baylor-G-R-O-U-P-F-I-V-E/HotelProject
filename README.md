# G.R.O.U.P. F.I.V.E. Hotel Project

Created by:  
[Afraz Iqbal]()  
[Brendon Newton]()  
[Chase Crayne](https://github.com/Starkrights)  
[Cole Flenniken]()  
[Siri Kothapalli]()  
[Icko Iben](https://github.com/ickoxii)  

## Contents

1. [Cloning This Repository Locally](./Installation.md)
2. [Usage](#usage)
3. [About This Project](#about-this-project)
    * [The Stay & Shop Reservation System](#the-stay-shop-reservation-system)
        * [Hotel Section](#hotel-section)
4. [Development Process](#development-process)
    1. [Iteration 1](#iteration-1)
    2. [Iteration 2](#iteration-2)
    3. [Iteration 3](#iteration-3)

## Usage

```zsh
make <target>
```

## About This Project

This project is a 3-layered application with Java SE. The data models contain 
at least seven data entities/concepts with one-to-many and many-to-many 
association mapping. Morever, the project must demonstrate the use of JUnit 
tests, git, JavaDoc, polymorphism, and Maven.  

### The Stay & Shop Reservation System

You have been contracted to create a system that automates the reservation 
process for a combined store and hotel. The establishment offers a unique 
experience where guests can stay in comfortable rooms and also have access to 
an exclusive shopping experience within the same premises.  

#### Hotel Section

The establishment has a total of three floors with hotel rooms. Each floor has 
a unique theme:  

1. **Nature Retreat**: Single, double, and family rooms available. Each room 
   has a nature-inspired design.
2. **Urban Elegance**: Suite and deluxe rooms available. Modern and elegant 
   room designs.  
3. **Vintage Charm**: Standard and deluxe rooms available. Rooms with a vintage 
   touch. Each room has standard hotel features like a unique number, type of 
   beds (single, double, queen), and smoking/non-smoking status.  

Each room is assigned a quality level:  

1. Executive level
2. Business level
3. Comfort level
4. Economy level  

Each room also has the following:  

1. A unique room number
2. A certain number and type of beds (i.e. twin, full, queen, king)
3. A smoking/non-smoking status  

Each quality level has a maximum daily rate, although the rate that a guest 
pays may be less (e.g., through promotion rate, group rate, or non-refundable 
rate). When a guest wishes to make a reservation, the travel agency asks the 
guest which nights they want to stay and the type of room desired. The system 
must verify if any room is available on those nights before allowing a 
reservation. The system needs to record basic information about a guest who has 
made a reservation: name, address, credit card number, and date of expiration. 
A reservation can be canceled at any time. There is no charge for cancellation 
if it is canceled within 2 days of the reservation date. The guest is charged 
80% of the cost of a single-night stay (at the reservation rate) if the 
cancellation is made after 2 days from the date on which the reservation was 
made. A reservation can be modified at any time. When a guest checks in, a room 
is allocated to the guest until the guest checks out. The system must keep track 
of all reservations and payments made and be able to provide a summary to the 
Cruice's manager when it is requested. Corporate guests are not directly billed; 
rather, the corporations they work for are billed, and payments are made sometime 
later.  

**Store Section**: The store offers a variety of products ranging from clothing 
and accessories to local artisanal goods. Guests have the option to explore and 
shop during their stay.  

## Development Process

### Iteration 1

The project vision, requirements (user stories in Trello accepted), use cases 
with business constraints, Use Case diagram, Domain model, SSDs, system operations 
with operation contracts, design class diagram, wireframes, each of the members 
will implement three non-basic use cases and related SSD and other artifacts 
(also implement them in the project).   

Presentation of iteration 1 (project - analysis, User Interface sketches, domain 
model, SSD, operations, requirements, use cases, keep zip for iteration 1 on the 
website) [10min]   

Include in your presentation all issues in your ticketing system (open, resolved), 
the number of commits, and the roster of hours worked by each member, including 
Trello history.  

### Iteration 2

Sequence diagrams, design model, package diagram and architecture, Demo of the 
current prototype, identification of GRASP patterns, the revised analysis.  

Presentation of iteration 2 (project - implementation, testing, maven, git, GUI, 
prototype, all relevant diagrams, keep zip for iteration 2 on the website) [10min].   

Include in your presentation all issues in your ticketing system (open, resolved), 
the number of commits, and hours worked by each member.   

### Iteration 3

Persistence (storage), Business layer, Testing + Logging described and implemented, 
UI, User Guide, revised design and analysis, three use cases each member – 
recorded as a video.  

Presentation of iteration 3 (project - implementation, maven, git, GUI, prototype, 
testing, all diagrams due on the website) [10min].  

* The last presentation needs to have a video showing 3 use cases per member for 
acceptance testing posted on your website. 
* I do want to see your implementation and functionality, show me what you are selling.  

Include in your presentation all issues in your ticketing system (open, resolved), 
the number of commits, and hours worked by each member.  
