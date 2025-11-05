# Cargo Management System

![Java](https://img.shields.io/badge/language-Java-blue.svg)
![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)
![Issues](https://img.shields.io/badge/issues-0-success.svg)

A simple, console-based application for managing cargo shipments. The Cargo Management System allows users to track and update shipments using a lightweight Java CLI. All data is stored locally in a text file (`Kargo.txt`).

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
- [Support & Documentation](#support--documentation)
- [Contributing](#contributing)
- [Maintainers](#maintainers)

---

## Overview

Cargo Management System is designed to help users manage cargo logistics without the need for web servers or external databases. It’s suitable for learning, prototyping, or small-scale cargo operations.

---

## Features

- **File-Based Persistence**  
  Data is stored in `Kargo.txt` in the project root.

- **Cargo Management**  
  Add, update, and view cargo shipment details.

- **Package Tracking**  
  Check the status of any shipment by ID.

- **Console Interface**  
  Interact using clear commands in the terminal.

---

## Getting Started

### Prerequisites

- **Java Development Kit (JDK):** v8 or newer
- **Git**

### Installation

1. **Clone the Repository**
   ```sh
   git clone https://github.com/Baran-sys/Cargo-Management-System.git
   cd Cargo-Management-System
   ```

2. **Compile Sources**
   - Create a directory for compiled classes:
     ```sh
     mkdir bin
     ```
   - Compile all Java files inside `src`:
     ```sh
     # macOS/Linux
     javac -d bin $(find src -name "*.java")
     # Windows (PowerShell)
     # Get-ChildItem -Recurse -Filter *.java | ForEach-Object { javac -d bin $_.FullName }
     ```

3. **Run the Application**
   ```sh
   java -cp bin Main
   ```
   <sub>Replace `Main` with your main class name if different.</sub>

   If your main class is packaged (e.g., `com.cargo`):
   ```sh
   java -cp bin com.cargo.Main
   ```

### Usage Example

Upon starting the application, use the terminal to add, view, or track shipments as prompted.

---

## Support & Documentation

If you encounter bugs, need features, or have questions:

- [Open an Issue](../../issues)
- Refer to the project [Wiki](../../wiki) for additional tips

---

## Contributing

We welcome contributions!

- Please see `docs/CONTRIBUTING.md` for complete guidelines.
- Fork the project, create a feature branch, make your changes, and open a pull request.

---

## Maintainers

- **Project Owner:** [Baran-sys](https://github.com/Baran-sys)

Feel free to [contact](https://github.com/Baran-sys) the owner via GitHub for major issues or collaboration proposals.

---

## License

This project is currently **unlicensed**. See the `LICENSE` file for details.
