# AI Lab Assistant - Tinkerers' Lab, PREC Loni

Welcome to the **AI Lab Assistant** repository of **Tinkerers' Lab, PREC Loni**! 🚀 This project is a **Spring Boot-based RAG (Retrieval-Augmented Generation) LLM model**, designed to assist students and researchers by providing contextual responses based on academic materials, research papers, and lab resources.

## ✨ Features
- **RAG-Powered AI**: Combines retrieval-based search with a powerful LLM for accurate and contextual responses.
- **Spring Boot Backend**: A robust and scalable backend built using Java Spring Boot.
- **Vector Database Integration**: Uses `pgvector` with PostgreSQL to store and retrieve embeddings efficiently.
- **Local Embedding Generation**: Utilizes `nomic-embed-text` to generate embeddings without relying on external API keys.
- **PDF Processing**: Scans and processes PDFs to extract relevant knowledge for AI-assisted answers.

## 📁 Repository Structure
```
├── src/main/java/com/TinkerersLab/LabAssistant  # Backend Spring Boot code
│   ├── config/                                 # Configuration files
│   ├── controller/                             # REST API controllers
│   ├── service/                                # Business logic
│   ├── model/                                  # Data models
│   ├── util/                                   # Utility functions
│   ├── LabAssistantApplication.java            # Application Entrypoint
│
├── data/                                       # PDF and document storage
├── Dockerfile                                  # Docker setup for containerization
├── README.md                                   # Project documentation
├── comose.yaml                                 # Docker configuration of services
├── pom.xml                                     # Project dependency configuration
└── .env                                        # Environment variables
```

## 🚀 Getting Started
### Prerequisites
- Java 17+
- Spring Boot 3+
- PostgreSQL with `pgvector`
- Docker (optional, for containerized deployment)
- Ollama

### Installation & Setup
1. **Clone the Repository**
   ```sh
   git clone https://github.com/tinkererslab/ai-lab-assistant.git
   cd ai-lab-assistant
   ```
2. **Set Up the Database**
   - Install PostgreSQL and enable `pgvector` extension.
   - Create a new database and update `.env` with database credentials.
3. **Run the Application**
   ```sh
   ./mvnw spring-boot:run
   ```
4. **Access the API**
   - API runs on `http://localhost:8080/api/`

## 📌 Future Enhancements
- **Fine-tuning the LLM** for improved contextual accuracy.
- **Integration with Chat UI** for a seamless user experience.
- **Multi-Document Support** to process various academic file formats.

## 🤝 Contributing
We welcome contributions from the community! Feel free to fork this repository, submit pull requests, or open issues.

## 📜 License
This project is open-source and available under the **MIT License**.

---
🚀 Built with passion at **Tinkerers' Lab, PREC Loni**. Let's innovate together! 🔥
