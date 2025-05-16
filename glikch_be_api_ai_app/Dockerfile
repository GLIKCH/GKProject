# Use an official Python runtime as a parent image
FROM python:3.11-slim

# Set working directory inside container
WORKDIR /app

# Copy requirements file and install dependencies
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copy the rest of the backend code into container
COPY . .

# Expose port 5000 to the outside
EXPOSE 5000

# Run the Flask app
CMD ["python", "run.py"]
