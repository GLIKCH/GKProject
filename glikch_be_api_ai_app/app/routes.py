# routes.py
# API Endpoints for GLIKCH Backend
# Last Updated: 03 / 28 / 2025

from flask import Blueprint, jsonify, current_app
from .models import format_product
from bson import ObjectId
from pymongo.errors import PyMongoError

# Create a Flask Blueprint for main routes
main = Blueprint("main", __name__)

@main.route("/", methods=["GET"])
def home():
    return jsonify({"message": "GLIKCH API is running!"})

@main.route("/products", methods=["GET"])
def get_products():
    """
    Fetch all products from the 'products' collection.
    """
    try:
        db = current_app.db  # Use .db instead of .mongo
        products_cursor = db["products"].find()
        products = [format_product(prod) for prod in products_cursor]
        return jsonify(products), 200
    except PyMongoError as e:
        return jsonify({"error": str(e)}), 500
