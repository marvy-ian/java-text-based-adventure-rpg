# Contributing to Java Text Adventure RPG

Thank you for considering contributing to the Java Text Adventure RPG project! Contributions are what make the open-source community such an amazing place to learn, inspire, and create.

---

## How Can I Contribute?

### 1. Reporting Bugs
If you find a bug or unexpected behavior:
- Open an issue using a clear title and descriptive details.
- Include steps to reproduce the issue (e.g., exact console commands or game state leading to the bug).
- Share details about your Java environment (`java -version`).

### 2. Suggesting Enhancements
Feature requests are always welcome!
- Check existing issues to verify your feature hasn't already been suggested.
- Describe the proposed feature, why it would be useful, and how it might fit into the existing architecture (e.g., adding dynamic equipment slots, new enemy types, or spell casting).

### 3. Submitting Pull Requests
Follow these steps to submit a pull request (PR):

1. **Fork the Repository:** Create your own copy of the project.
2. **Create a Feature Branch:** 
   ```bash
   git checkout -b feature/amazing-new-feature
   ```
3. **Write Clean Code:**
   - Adhere to standard Java naming conventions (PascalCase for classes, camelCase for variables/methods).
   - Ensure all `Serializable` objects maintain explicit `serialVersionUID` declarations if modified.
   - Keep classes modular and focused on single responsibilities.
4. **Commit Your Changes:** Write descriptive commit messages using semantic prefixes (e.g., `feat:`, `fix:`, `docs:`).
   ```bash
   git commit -m "feat: add magic spell system to combat loop"
   ```
5. **Push to Your Branch:**
   ```bash
   git push origin feature/amazing-new-feature
   ```
6. **Open a Pull Request:** Describe the changes made and link to any relevant open issues.

---

## Coding Guidelines

- **Formatting:** Maintain consistent 4-space indentation.
- **Console IO:** Keep output formatting clean and readable using standard visual dividers (`===`, `---`).
- **Dependencies:** Avoid external third-party dependencies unless strictly necessary—the goal of this repository is to showcase vanilla Java proficiency.

Thank you for your help in building a better RPG!