# 📦 GLIKCH 2025 Project: Docker Volume & File Syncing Guide

Author: Joel J. De Alba  
Affiliations: Sightline Services LLC & GLIKCH Engineering Innovations LLC  
Institution: Southern New Hampshire University (SNHU)  
Contact: sightlinepcs@outlook.com  

---

## 📌 Overview

This document explains how file syncing works between the local development environment (VS Code) and running Docker containers in the GLIKCH 2025 Backend AI API project. It specifically focuses on **Docker bind mounts** and how they enable live file updates within a running container.

---

## 📊 How Bind Mount Syncing Works

A **bind mount** directly connects a local folder from the host machine to a directory inside a Docker container.

### Example from `docker-compose.yml`:

```yaml
services:
  backend:
    volumes:
      - .:/app

Explanation:
```

The local project folder (.) is mounted to the /app directory inside the backend container.

Any file changes in VS Code are instantly reflected in the container’s /app directory.

No rebuild or restart is required for changes to sync if the app runs in debug mode.

## 📌 Syncing Behavior Matrix

# Action	Requires Docker?	Container Running?	Sync Happens?
# Edit file in VS Code	  No	No	No
# Docker running only	  Yes	No	No
# Docker + container up	  Yes	Yes	✅ Yes

## ⚙️ Is Docker Required for Syncing?

# Yes.

# The connection between your local files and the container’s filesystem only exists while:

Docker is running

The container is actively running with the volume mount defined in docker-compose.yml

If Docker or the container isn’t running, the sync connection is inactive.

## 📄 File Editing Flow with Bind Mount

Developer edits a file locally in VS Code.

The bind mount instantly updates the container’s /app directory.

If Flask is running with debug=True or flask run --reload, it auto-detects changes and reloads the server.

No need to rebuild the container unless dependencies or system-level configurations change.

## 📌 Bind Mount vs. Named Volume

# Bind Mount	Named Volume
# Connects host directory → container	Docker-managed persistent storage
# Great for live development sync	Great for database persistence and config files
# Resides inside project folder	Resides in Docker storage space

## 📎 Important Notes

# No Docker → No Container → No Sync

# Docker Compose must be actively running the container for the live connection to work.

Always confirm your container logs or status in Docker Desktop when troubleshooting sync issues.

## 📚 Related Commands

# docker compose up
# docker compose down
# docker compose up --build
# docker ps

## ✅ Summary

# Docker bind mounts offer a seamless, efficient workflow for live application development in containerized environments. This document ensures the GLIKCH 2025 project maintains a secure, documented, and scalable development workflow in line with DevSecOps best practices.