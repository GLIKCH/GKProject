# ==============================================================================
# GLIKCH 2025 Project Development - Joel J. De Alba
# Bachelors of Computer Science: Software Engineering, DevSecOps / Cybersecurity,
# AI and Robotics Engineering
# Registered under Sightline Services LLC and GLIKCH Engineering Innovations LLC
# Contact: sightlinepcs@outlook.com
# Institution: Southern New Hampshire University (SNHU)
#
# Module: app/__init__.py
# Description:
# Defines the application factory `create_app` for the GLIKCH Backend.
# Loads environment variables, initializes the Flask app, enables CORS,
# connects to MongoDB, and registers API blueprints.
# This design supports scalability, configurability, and modularity.
# ==============================================================================

from flask import Flask
from flask_cors import CORS
from pymongo import MongoClient
from dotenv import load_dotenv
import os

def create_app():
    """
    Application factory function to create and configure
    the GLIKCH Backend Flask application instance.

    Returns:
        app (Flask): Configured Flask application.
    """
    # Load environment variables from a .env file into the process environment
    load_dotenv()

    app = Flask(__name__)

    # Enable Cross-Origin Resource Sharing (CORS)
    # Allows frontend clients from different origins to interact with this API.
    # Restrict allowed origins in production for improved security.
    CORS(app)

    # Load MongoDB URI from environment variable or fallback to local default
    mongo_uri = os.getenv("MONGO_URI", "mongodb://localhost:27017/")
    mongo_client = MongoClient(mongo_uri)

    # Connect to MongoDB database designated for GLIKCH backend
    app.db = mongo_client["glikch_db"]

    # Import and register API routes as a Flask Blueprint for modularity
    from .routes import main
    app.register_blueprint(main)

    return app
