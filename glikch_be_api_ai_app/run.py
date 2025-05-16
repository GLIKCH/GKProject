# ==============================================================================
# GLIKCH 2025 Project Development - Joel J. De Alba
# Bachelors of Computer Science: Software Engineering, DevSecOps / Cybersecurity,
# AI and Robotics Engineering
# Registered under Sightline Services LLC. and GLIKCH Engineering Innovations LLC.
# Contact: sightlinepcs@outlook.com
# Institution: Southern New Hampshire University (SNHU)
#
# Module: run.py
# Description:
# This script serves as the entry point for the GLIKCH Backend API application.
# It imports the Flask application factory method `create_app` from the `app` package,
# initializes the application instance, and runs the server in debug mode.
# Debug mode is enabled for development purposes to allow real-time error reporting
# and auto-reloading on code changes.
# ==============================================================================

from app import create_app  # Import the application factory function from the app package

# Instantiate the Flask application using the factory pattern
app = create_app()

# Entry point: Run the Flask development server if this script is executed directly
if __name__ == "__main__":
    app.run(debug=True)  # Enable debug mode for development
