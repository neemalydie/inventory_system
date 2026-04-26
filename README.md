#Airtel Inventory Management System (IMS)
A desktop-based offline inventory system for tracking Airtel's end-user devices (laptops, desktops, mobile phones) with assignment tracking and reporting.



## Access Link
     http://localhost:8081/

---
##Database structure
DATABASE NAME: `ims`

####Table realationships
    users     → independent (authentication only)
    devices   → one-to-many → assignments
    employees → one-to-many → assignments
    assignments → belongs to → devices & employees
---

##  Admin User (Full Access)

    | Feature | Permission |
    |---------|------------|
    | View Dashboard | YES |
    | Register Device | YES |
    | Edit Device | YES |
    | Delete Device | YES |
    | Register Employee | YES |
    | Edit Employee | YES |
    | Delete Employee | YES |
    | Assign Device | YES |
    | Return Device | YES |
    | View Devices List | YES |
    | View Employees List | YES |
    | View Active Assignments | YES |
    | Search & Filter | YES |
    | Generate Reports | YES |

---

##  Staff User (Read-Only)

    | Feature | Permission |
    |---------|------------|
    | View Dashboard | YES |
    | Register Device | NO |
    | Edit Device | NO |
    | Delete Device | NO |
    | Register Employee | NO |
    | Edit Employee | NO |
    | Delete Employee | NO |
    | Assign Device | NO |
    | Return Device | NO |
    | View Devices List | YES |
    | View Employees List | YES |
    | View Active Assignments | YES |
    | Search & Filter | YES |
    | Generate Reports | YES |

---
## Credentials:
    24RP00844 / 24RP06514/(Role:Admin)  
    24RP06514 / 24RP00844/(Role:STAFF user)  
---
## A1 Group Members

    | # | Full Name | Registration Number |
    |---|-----------|---------------------|
    | 1 | MUNYANEZA Nelson | 24RP00844 |
    | 2 | NEEMA Lydie | 24RP06514 |