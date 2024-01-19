
# User Diagrams

## User Login
### Success
```mermaid
sequenceDiagram

participant User
participant Frontend
participant Backend

User->>Frontend: Submit Login information
Frontend->>Backend: Provide login information<br/> for authentication
Backend->>Frontend: Provide valid session token<br/> with relevant user details.
Frontend->>User: Display the user's <br/>landing page (admin/clerk/customer)
```

<details>
  <summary>
  Notes:
  </summary>
- We can either provide different login portals with different DBs for User/Clerk/Admin, or a singular login portal, where the backend sends the user's Authorized credentials to the frontend.
</details>

### Fail
```mermaid
sequenceDiagram

participant User
participant Frontend
participant Backend

User->>Frontend: Submit Login information
Frontend->>Backend: Provide login information<br/> for authentication
Backend->>Frontend: Respond error invalid credentials.
Frontend->>User: Display error page to user
```
