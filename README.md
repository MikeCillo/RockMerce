# 🌱 RockMerce: Sustainable Software Engineering Project

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Green Coding](https://img.shields.io/badge/Green_Coding-Enabled-2ea44f?style=for-the-badge)
![SonarQube](https://img.shields.io/badge/Quality_Gate-Passed-4E9BCD?style=for-the-badge&logo=sonarqube&logoColor=white)
![Coverage](https://img.shields.io/badge/Coverage-88.8%25-brightgreen?style=for-the-badge)

> **From Legacy to Green:** Transforming an e-commerce platform into a sustainable, efficient, and inclusive software ecosystem.

## 📖 Project Overview
## 🎸 About RockMerce

**RockMerce** is a web-based e-commerce platform dedicated to musical instruments, specifically designed for guitar enthusiasts.
Born as a university project for the **Sustainable Software Engineering** course, it serves as a practical case study on how to transform a "Legacy" software into a "Green" software without compromising functionality.

### 🛒 Core Functionalities
The application offers a complete B2C (Business-to-Consumer) shopping experience:

* **Product Catalog:** Browse a wide range of guitars categorized by type (Electric, Acoustic, Semi-Acoustic) and brand.
* **Smart Filtering:** Users can filter products by category or search by name.
* **Shopping Cart:** Full management of the cart (add items, update quantities, remove products, calculate dynamic totals).
* **Checkout Process:** Secure order placement and summary generation.
* **User Accounts:**
    * **Customers:** Registration (with gender-inclusive options), Login, and Order History.
    * **Admins:** Dedicated dashboard to manage the product inventory (CRUD operations: Create, Read, Update, Delete guitars).

### 🌿 Sustainability Features (The "Green" Edge)
Unlike standard e-commerce sites, RockMerce integrates unique features to minimize environmental impact and maximize social inclusion:

* **Native Dark Mode:** A specifically designed OLED-friendly color palette (Soft Black background) to reduce screen energy consumption on modern devices.
* **Eco-Awareness Badge:** A real-time "EcoIndex" indicator in the footer that informs users about the CO₂ impact of their navigation, promoting digital awareness.
* **Accessibility Toolbar:** A custom tool allowing visually impaired or color-blind users to toggle a **High Contrast Mode**, ensuring the site is compliant with WAI-ARIA standards (Social Sustainability).
* **Second Hand Economy:** A dedicated section (simulated) promoting the repair and reuse of instruments to support the Circular Economy (SDG 12).
---

## 📊 Key Results (Before vs. After)

We measured the impact of our refactoring using **SonarQube (with Creedengo)**, **JMeter**, and **EcoIndex**.

| Metric | Legacy System (Baseline) | Green Refactored (Final) | Improvement |
| :--- | :---: | :---: | :---: |
| **CO₂ per Visit** | 0.99g | **0.10g** | 📉 **-90%** |
| **Reliability Rating** | E (129 Bugs) | **A (0 Bugs)** | ✅ **Fixed** |
| **Test Coverage** | 0% | **88.8%** | 📈 **+88%** |
| **Code Smells** | 963 | **552** | 🧹 **-411** |
| **Technical Debt Cost** | ~€ 31,820 | **~€ 11,040** | 💰 **-65% Savings** |
| **EcoIndex Score** | E | **B (75/100)** | 🌍 **Green** |

---

## 🛠️ Technical Interventions (Green Coding)

### 1. Backend Optimization (Energy Efficiency)
* **Resource Management:** Implemented **Try-With-Resources** in all DAO classes to eliminate memory leaks and ensure immediate DB connection closure.
* **Loop Invariant Code Motion:** Extracted redundant operations (e.g., connection setup, parameter setting) outside of loops to reduce CPU cycles.
* **ACID Transactions:** Refactored business logic (`addGuitarToCart`) to use a single transactional connection instead of multiple disjointed connections.
* **Optimized Queries:** Replaced `SELECT *` with specific projections to reduce data transfer overhead.

### 2. Frontend Modernization (Digital Wellbeing)
* **Dark Mode & Palette:** Implemented a low-energy OLED-friendly palette (Soft Black/Warm Beige).
* **Asset Diet:** Removed heavy videos and animations, reducing page weight from **11.6MB to 1.1MB**.
* **Accessibility:** Added WAI-ARIA tags and a custom **High Contrast Toolbar** for color-blind users (Social Sustainability).

### 3. Quality Assurance
* **Static Analysis:** Integrated SonarQube with the **Creedengo** plugin to detect green-specific code smells.
* **Testing:** Developed a full JUnit 5 test suite covering ~89% of instructions (focusing on Critical DAOs).
* **Benchmarking:** Used **JMH** (Java Microbenchmark Harness) and **Apache JMeter** to validate performance vs. reliability trade-offs.

---

## 🧰 Tech Stack & Tools

* **Language:** Java (JDK 17+), JSP, Servlet
* **Database:** MySQL
* **Testing:** JUnit 5, Mockito, JMH, Apache JMeter
* **Static Analysis:** SonarQube (Dockerized) + Creedengo Plugin
* **Coverage:** JaCoCo
* **Methodology:** SusAF (Sustainability Awareness Framework)

---

## 🚀 How to Run

### Prerequisites
* Java JDK 22+
* Apache Tomcat 9+
* MySQL Server
* Docker (optional, for SonarQube analysis)

### Installation
1.  **Clone the repository**
    ```bash
    git clone [https://github.com/MikeCillo/RockMerce.git](https://github.com/MikeCillo/RockMerce.git)
    cd RockMerce
    ```

2.  **Database Setup**
    * Import the script `db/rockmerce_schema.sql` into your MySQL instance.
    * Update `DbConnection.java` with your credentials.

3.  **Run with Tomcat**
    * Deploy the `war` file or configure the project in your IDE (IntelliJ/Eclipse) with a Tomcat run configuration.

4.  **Run Tests**
    ```bash
    mvn  ./run_jmh.sh login - ./run_jmh.sh cart
    ```

---

## 👥 Authors

* **Michele Cillo** - [GitHub Profile](https://github.com/MikeCillo)

---

## 📄 License

This project is part of the **Sustainable Software Engineering** course. Università degli Studi di Salerno
Distributed under the MIT License.
