---  
layout: home
permalink: index.html

# Please update this with your repository name and title
repository-name: e19-CO227-A-Simple-Web-or-Mobile-Application-For-A-Gym
title: GymHub   
---

[comment]: # "This is the standard layout for the project, but you can clean this and use your own template"

# GymHub
![logo](https://github.com/cepdnaclk/e19-CO227-A-Simple-Web-or-Mobile-Application-For-A-Gym/assets/111074993/f1c64a2a-440f-41b6-b325-4cd0a99105ce)

---

<!-- 
This is a sample image, to show how to add images to your page. To learn more options, please refer [this](https://projects.ce.pdn.ac.lk/docs/faq/how-to-add-an-image/)

![Sample Image](./images/sample.png)
 -->

## Group 13

## Team
-  E/19/074, Dharmarathne B.A.M.I.E., [email](mailto:e19074@eng.pdn.ac.lk)
-  E/19/124, Gunasekara M.H., [email](mailto:e19124@eng.pdn.ac.lk)
-  E/19/166, Jayathunga W.W.K., [email](mailto:e19166@eng.pdn.ac.lk)
-  E/19/424, Weerasinghe H.A.S.N., [email](mailto:e19424@eng.pdn.ac.lk)

## Table of Contents
1. [Introduction](#introduction)
2. [Problems With The Existing Systems](#problems_with_the_existing_systems)
3. [Our Goals](#our_goal)
4. [Background & Motivation](#background_&_motivation)
5. [Progress](#progress)
6. [Proporsed Solution](#proporsed_solution)
7. [Overview & Key Features](#overview_&_key_features)
8. [Requirement Gathering](#requirment_gathering)
9. [Advantages](#advantages)
10. [Additional Features](#additional_features)
11. [Technology Stack](#technology_stack)
12. [High Level Architectute](#high_level_architecture)
13. [Data & Control Flow](#data_&_control_flow)
14. [Timeline](#timeline)
15. [Team & Process](#team_&_process)
16. [Links](#links)

---

## Introduction

 description of the real world problem and solution, impact

## Problems With The Existing Systems

- Lack of Reminders
- Communication Barriers
- Insufficient Progress Tracking
- Appointment Scheduling Challenges  

## Our Goals

- Streamline Gym Operations and User Experience
- Enhance Progress Tracking and Fitness Management
- Ensure Data Security and Privacy
- Expand User Base and Engagement     

## Background & Motivation

- [Equinox](https://www.equinox.com/)
- [Anytimefitness](https://www.anytimefitness.com/)

## Progress

- Use of modern technologies for frond-end,back-end and database
- Continuous engagement through progress tracking promotes long-term commitment
- Fitness app usage rising, since  demand  for easy tracking and Minimal direct competition identified
- Fitness app usage rising, since  demand  for easy tracking and Minimal direct competition identified

## Proporsed Solution

- Real-time progress tracking through fitness logs and measurements
- Sync with google calendar
- Personalized workout plan(amount of calorie burn)and goal setting
- notifications/email for upcoming appointments and reminders
- user friendly  interface for easy appointment scheduling
- BMI calculator



### Homepage

![HOME 1](https://github.com/cepdnaclk/e19-CO227-A-Simple-Web-or-Mobile-Application-For-A-Gym/assets/111074993/92302bf8-142e-4507-9385-b2a30e767485)

### HomePage for the trainer

![TRAINER 1](https://github.com/cepdnaclk/e19-CO227-A-Simple-Web-or-Mobile-Application-For-A-Gym/assets/111074993/ea03a3f5-a113-450b-9df1-c389ea93fe12)

### Available trainees for a paticular trainer

![TRAINER 2](https://github.com/cepdnaclk/e19-CO227-A-Simple-Web-or-Mobile-Application-For-A-Gym/assets/111074993/000488b9-3ded-40d0-821a-dc14d0d7f430)

### HomePage for the trainee

![TRAINEE 1](https://github.com/cepdnaclk/e19-CO227-A-Simple-Web-or-Mobile-Application-For-A-Gym/assets/111074993/9102de4e-9c29-40cc-a4c7-85b7a249aba3)

### Exercise Tracking

![TRAINEE 3](https://github.com/cepdnaclk/e19-CO227-A-Simple-Web-or-Mobile-Application-For-A-Gym/assets/111074993/7d439652-0934-4e0a-96aa-ec31e4a0f1c0)

### Progress Tracking

![TRAINEE 2](https://github.com/cepdnaclk/e19-CO227-A-Simple-Web-or-Mobile-Application-For-A-Gym/assets/111074993/a30950cd-c899-413e-ba77-fc8dae63bc8d)

### Users 

- Trainer
- Gym Member

### Features of Trainer

- Create user Profiles
- Mark the available time slots
- Accept & decline the appointment
- Receive reminders

### Features of Gym Member

- Create user profile
- Check availability of a trainer
- Make the appointments
- Weight tracking

## Overview & Key Features

### GymWeb App Overview

- -Your All-in-One Fitness Companion

### Key Features & Functionalities

- User Authentication 
              -Secure registration and login
- Personalized Workouts
              -Customize routines for your goals
- Trainer Interaction
              -Expert guidance and training plans
- Progress Tracking
              -Visualize and monitor your fitness journey

## Requirement Gathering

- Understanding the overarching goals of the gym management system and followed the requirements that are given in document       
- Document both functional and nonfunctional requirements                            
- Meetings with supervisors

## Advantages

- Convenience of booking sessions and monitoring fitness goals
- Receiving reminders & user friendly interface
- Monetization Strategy
- User Experience and Usebility
- Integration with Existing System

## Additional Features

- Notify with emails
- Sync with google calender
- BMI calculator
- Remind required amount of calorie burn
- Get customer feedbacks
- AI Trainer

## Technology Stack

- Figma
- React JS
- Spring Boot
- JWT
- GitHub
- Swagger

## High Level Architecture

![Hightlevel Architecture](https://github.com/cepdnaclk/e19-CO227-A-Simple-Web-or-Mobile-Application-For-A-Gym/assets/111074993/15edd980-a4d1-4452-87a7-f3ab7a06c35f)

•	Users interact with the Gym App through web browsers or mobile devices (represented as "User Devices").
•	The "Frontend" is responsible for providing a user-friendly interface, collecting user input, and displaying information to users.
•	The "Backend" acts as the brain of the system. It manages the entire application, handles communication between the frontend and other components, and implements core business logic.
•	The "AI Trainer" is an intelligent component that uses OpenCV and Python to provide fitness-related assessments and recommendations. It communicates with the backend to process data and deliver results.
•	The "MySQL Database" stores all essential data, including user profiles, appointments, and weight tracking records. The backend interacts with the database to retrieve and store data.
•	External services, such as "Reminders," integrated to enhance the user experience by sending notifications or reminders to users.

## Data & Control Flow

- User Registration
   1.	A new gym member enters their registration details via the frontend (e.g., name, email).
   2.	The frontend sends this data to the backend as an HTTP POST request.
   3.	The backend validates and processes the registration request.
   4.	User profile data, including the unique identifier, is stored securely in the database.
   5.	Confirmation is sent from the backend to the frontend, notifying the user of successful       registration.

- Appointment Scheduling
   1.	A gym member selects an available time slot and trainer for an appointment.
   2.	The frontend sends a request to the backend, specifying the desired date, time, and           participants.
   3.	The backend checks trainer availability and conflicts in the database.
   4.	If the time slot is available, the backend approves the appointment and records it.
   5.	Both the gym member and trainer receive notifications of the scheduled appointment.

- Weight Tracking


![dataflow](https://github.com/cepdnaclk/e19-CO227-A-Simple-Web-or-Mobile-Application-For-A-Gym/assets/111074993/6c741b85-02d8-42c6-9479-87e4246e4c2b)

## Timeline

![timeline](https://github.com/cepdnaclk/e19-CO227-A-Simple-Web-or-Mobile-Application-For-A-Gym/assets/111074993/431e2952-8a2d-418f-b2c6-d24f3a8152ea)

## Team & Process

- Dharmarathne B.A.M.I.E. - Home, About, Gallery
- Gunasekara M.H.         - Presentation Design, Wiki Pages Design, Courses
- Jayathunga W.W.k.       - Presentation Design, Readme Design, Git Page Design, UI Design, User Profiles
- Weerasinghe H.A.S.N.    - Presentation Design, Pricing


## Links

- [Project Repository](https://github.com/cepdnaclk/e19-CO227-A-Simple-Web-or-Mobile-Application-For-A-Gym)
- [Project Page](https://cepdnaclk.github.io/e19-CO227-A-Simple-Web-or-Mobile-Application-For-A-Gym/)
- [Department of Computer Engineering](http://www.ce.pdn.ac.lk/)
- [University of Peradeniya](https://eng.pdn.ac.lk/)


[//]: # (Please refer this to learn more about Markdown syntax)
[//]: # (https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)
