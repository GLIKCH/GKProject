# MongoDB Product Schema & Formatter
# Last Updated: 03 / 28 / 2025

# Defines how the product data is structured in MongoDB
# Also includes a formatter to cleanly return it as JSON

def format_product(product):
    return {
        "_id": str(product.get("_id")),
        "name": product.get("name"),
        "description": product.get("description"),
        "image_url": product.get("image_url"),
        "price": product.get("price"),
        "discount_percent": product.get("discount_percent"),
        "discounted_price": product.get("discounted_price"),
        "stock": product.get("stock"),
        "variants": product.get("variants", []),
        "shipping_cost": product.get("shipping_cost"),
        "tax_cost": product.get("tax_cost"),
        "state_tax_percent": product.get("state_tax_percent")
    }
