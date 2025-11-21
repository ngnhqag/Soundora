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
