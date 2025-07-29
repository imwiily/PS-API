ALTER TABLE categories
ADD COLUMN category_description VARCHAR(3000),
ADD COLUMN category_image_url VARCHAR(300),
ADD COLUMN category_total_products INTEGER,
ADD COLUMN category_active BOOL;