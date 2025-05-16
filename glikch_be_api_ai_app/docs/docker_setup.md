# GLIKCH 2025 Backend Docker Environment Setup

**Author:** Joel J. De Alba  
**Organization:** Sightline Services LLC / GLIKCH Engineering Innovations LLC  
**Contact:** sightlinepcs@outlook.com  
**Institution:** Southern New Hampshire University (SNHU)

---

## 📑 Overview

This document outlines the Docker Compose setup for the GLIKCH 2025 Backend Application. It covers service definitions, volume mappings, and environment configurations to enable seamless local development with persistent MongoDB data storage and live backend development.

---

## 📦 Services

### 1️⃣ Backend (Flask + AI API)
- **Build Context:** Current project directory (`.`)
- **Ports:** 5000 (host) → 5000 (container)
- **Environment Variables:**
  - `MONGO_URI`
  - `FLASK_ENV`
  - `SECRET_KEY`
- **Volumes:**
  - **Bind Mount:** Syncs local code with container at `/app` for live development.

### 2️⃣ MongoDB
- **Image:** `mongo:latest`
- **Ports:** 27017 (host) → 27017 (container)
- **Volumes:**
  - **Named Volume:** `mongo_data` persists MongoDB data outside of the container.

---

## 📂 Volumes Explained

| Type         | Purpose                                | Example                         |
|:--------------|:----------------------------------------|:----------------------------------|
| Named Volume | Persist MongoDB data independently of containers | `mongo_data:/data/db`             |
| Bind Mount   | Sync local backend code with container for live updates | `- .:/app`                        |

---

## 🔧 Usage

### Build and Start Containers:
```bash
docker compose up --build
```

## Subsequent Runs (without rebuilding):

docker compose up

## Stop and Remove Containers:

docker compose down

## 📝 Notes

# Environment variables are defined in a .env file at the project root.

# In production, remove bind mounts for security and performance.

# CORS should be configured to allow only trusted origins in production.

glikch_be_api_ai_app/
├── Dockerfile
├── docker-compose.yml
├── .env
├── app/
│   ├── __init__.py
│   ├── routes.py
│   └── ...
├── docs/
│   └── docker_setup.md
└── ...


---

## ✅ Next Step:  

version: "3.8"

services:
  backend:
    build: .
    volumes:
      - .:/app    # <--- live code sync
    environment:
      - MONGO_URI=${MONGO_URI}
      - FLASK_ENV=${FLASK_ENV}
      - SECRET_KEY=${SECRET_KEY}
    ports:
      - "5000:5000"
    depends_on:
      - mongo

  mongo:
    image: mongo
    restart: always
    volumes:
      - mongo_data:/data/db
    ports:
      - "27017:27017"

volumes:
  mongo_data:



