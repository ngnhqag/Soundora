# Soundora
Ứng dụng nghe nhạc

1. Language: Kotlin
2. UI: Jetpack Compose
3. Image Loading: Coil
4. Dependency Injection: Koin DI
5. Local DB: Room
6. Remote DB: FireStore
7. Api Call: Retrofit
8. Navigation: Navigation 3
9. Architecture: MVI + Clean Architecture
10. Language: Vietnamese + English

# Video Demo
- [Xem video demo tại đây](https://drive.google.com/file/d/1WCx_c52sGikOOd8J22odEfA-PbQEbm4S/view)
# APK
- [Download APK tại đây](https://1drv.ms/u/c/b130afdc324f2f68/IQB8_ay3-alCT6_fAwTLbgc0AW5bcHHEEhLpQKsuhheliSU?e=aJnsIw)




## 🏗️ Architecture Overview

```
┌─────────────────────────────────────────────────────────────┐
│                     Presentation Layer                     │
│  ┌─────────────────┐ ┌─────────────────┐ ┌──────────────┐  │
│  │       App       │ │   Features      │ │ UI Components│  │
│  │   (MainActivity)│ │ (Onboarding,    │ │ (Design      │  │
│  │                 │ │  Recording,     │ │  System)     │  │
│  │                 │ │  Folders)       │ │              │  │
│  └─────────────────┘ └─────────────────┘ └──────────────┘  │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                     Domain Layer                           │
│  ┌─────────────────┐ ┌─────────────────┐ ┌──────────────┐  │
│  │   Use Cases     │ │   Models        │ │ Repository   │  │
│  │ (Business Logic)│ │ (Pure Kotlin)   │ │ Interfaces   │  │
│  └─────────────────┘ └─────────────────┘ └──────────────┘  │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                      Data Layer                            │
│  ┌─────────────────┐ ┌─────────────────┐ ┌──────────────┐  │
│  │   Repository    │ │   Data Sources  │ │   Database   │  │
│  │ Implementation  │ │  (Local/Remote) │ │    (Room)    │  │
│  └─────────────────┘ └─────────────────┘ └──────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

```
Sample Flow: View ---> Intent ---> ViewModel(method processIntent()) ---> UseCase ---> Repository ---> DataSource
             View <---State; Event <--- 
```
