INSERT INTO category_tbl (name, description, color, created_at, updated_at) VALUES ('Technology', 'All about tech and gadgets', '#FF5733', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, created_at, updated_at) VALUES ('Health', 'Topics related to health and wellness', '#33FF57', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, created_at, updated_at) VALUES ('Lifestyle', 'Lifestyle tips and tricks', '#5733FF', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, created_at, updated_at) VALUES ('Education', 'Resources for learning and growth', '#FFD700', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, created_at, updated_at) VALUES ('Travel', 'Explore new places and adventures', '#00CED1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO role_tbl (role_name) VALUES ('USER_ROLE');
INSERT INTO role_tbl (role_name) VALUES ('ADMIN_ROLE');

INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('luis', 'luis@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('ana', 'ana@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('carlos', 'carlos@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('maria', 'maria@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user_role_tbl (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role_tbl (user_id, role_id) VALUES (2, 1);
INSERT INTO user_role_tbl (user_id, role_id) VALUES (3, 1);
INSERT INTO user_role_tbl (user_id, role_id) VALUES (4, 1);

-- Blog 1
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Tech Trends 2025', 'Latest trends in technology', 'Content about tech...', 'PUBLISHED', 'tech-trends-2025', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

-- Blog 2
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Healthy Living Tips', 'Tips for a healthier lifestyle', 'Content about health...', 'PUBLISHED', 'healthy-living-tips', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

-- Blog 3
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Travel Guide Europe', 'Exploring Europe destinations', 'Content about travel...', 'DRAFT', 'travel-guide-europe', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);

-- Blog 4
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Java Best Practices', 'How to write clean Java code', 'Content about Java...', 'PUBLISHED', 'java-best-practices', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

-- Blog 5
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Fitness for Beginners', 'Getting started with fitness', 'Content about fitness...', 'PUBLISHED', 'fitness-for-beginners', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

-- Blog 6
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Digital Marketing 101', 'Introduction to digital marketing', 'Content about marketing...', 'DRAFT', 'digital-marketing-101', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4);

-- Blog 7
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Education Trends', 'Trends in modern education', 'Content about education...', 'PUBLISHED', 'education-trends', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5);

-- Blog 8
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Gadget Reviews', 'Latest gadgets reviewed', 'Content about gadgets...', 'PUBLISHED', 'gadget-reviews', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

-- Blog 9
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Mindfulness Practices', 'How to practice mindfulness', 'Content about mindfulness...', 'DRAFT', 'mindfulness-practices', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

-- Blog 10
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Travel Asia', 'Exploring Asia destinations', 'Content about Asia travel...', 'PUBLISHED', 'travel-asia', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);


-- Blog 1 -> Tecnología, Educación
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (1, 1);
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (1, 4);

-- Blog 2 -> Salud
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (2, 2);

-- Blog 3 -> Viajes, Lifestyle
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (3, 5);
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (3, 3);

-- Blog 4 -> Tecnología
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (4, 1);

-- Blog 5 -> Salud, Lifestyle
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (5, 2);
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (5, 3);

-- Blog 6 -> Educación
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (6, 4);

-- Blog 7 -> Educación
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (7, 4);

-- Blog 8 -> Tecnología
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (8, 1);

-- Blog 9 -> Salud
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (9, 2);

-- Blog 10 -> Viajes
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (10, 5);

