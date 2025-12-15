# Atomberg Smart Fan Controller – Android App

## 📌 Overview
This Android application is developed as part of the **Atomberg Assignment**.  
The app allows users to **view, monitor, and control Atomberg smart fans** using official REST APIs.  
It follows **modern Android development best practices** with clean architecture and scalable code.

---

## 🚀 Features
- 🔐 Login using API Key and Access Token
- 📱 Fetch and display smart fan devices
- ⚡ Power ON / OFF control
- 🎚️ Set fan speed (absolute & relative)
- 🌙 Enable / Disable sleep mode
- 💡 Control LED light
- ⏲️ Set timer
- 📡 Detect and handle device online/offline status
- 💾 Secure token storage using Jetpack DataStore

---

## 🛠️ Tech Stack

### Android
- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** MVVM
- **Networking:** Retrofit + OkHttp
- **JSON Parsing:** Gson / Moshi
- **Dependency Injection:** Manual ViewModel Factory (No Hilt)
- **Local Storage:** Jetpack DataStore

---

### 📌 Architecture Notes
- **MVVM Pattern** used for clean separation of concerns
- **Repositories** handle API & data logic
- **ViewModels** manage UI state
- **Jetpack Compose** used for all UI components
- **DataStore** used for secure persistence

---

## 📦 APK Download
You can download and install the APK from the link below:

🔗 **APK Download:**  
https://drive.google.com/file/d/1mwhV0dNqiZjtKPZeKbp1NDRRfZIzPPv_/view?usp=sharing

> ⚠️ Make sure to enable **“Install from Unknown Sources”** on your Android device.

---

## 📥 Clone the Repository
Use the following command to clone the project locally:

```bash
git clone https://github.com/ramveerk7802/atomberg-app.git



