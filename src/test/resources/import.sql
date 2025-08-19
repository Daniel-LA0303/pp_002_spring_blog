INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('Technology', 'All about tech and gadgets', '#FF5733', 'TECH', 'Technology', 'Latest innovations in technology and gadgets.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('Health', 'Topics related to health and wellness', '#33FF57', 'HEALTH', 'Health', 'Articles and tips for a healthier lifestyle.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('Lifestyle', 'Lifestyle tips and tricks', '#5733FF', 'LIFE', 'Lifestyle', 'Inspiration and tips to improve daily living.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('Education', 'Resources for learning and growth', '#FFD700', 'EDU', 'Education', 'Guides, courses, and resources for lifelong learning.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('Travel', 'Explore new places and adventures', '#00CED1', 'TRAVEL', 'Travel', 'Travel guides and adventure stories from around the world.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('Java', 'Java programming language resources', '#F89820', 'JAVA', 'Java', 'Tutorials, libraries, and frameworks for Java development.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('Python', 'Python programming resources', '#3776AB', 'PYTHON', 'Python', 'Guides and tools for Python developers.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('JavaScript', 'JavaScript tips and frameworks', '#F7DF1E', 'JAVASCRIPT', 'JavaScript', 'Frontend and backend development with JavaScript.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('C#', 'C# programming resources', '#9B4F96', 'CSHARP', 'C#', 'Articles, tools, and examples for C# development.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('Go', 'Golang programming resources', '#00ADD8', 'GO', 'Go', 'Tips and frameworks for Go developers.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('Rust', 'Rust programming language resources', '#DEA584', 'RUST', 'Rust', 'Guides and libraries for Rust programming.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('Kotlin', 'Kotlin programming language resources', '#A97BFF', 'KOTLIN', 'Kotlin', 'Tips and tools for Kotlin developers.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('TypeScript', 'TypeScript tips and frameworks', '#3178C6', 'TYPESCRIPT', 'TypeScript', 'Frontend and backend development with TypeScript.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('SQL', 'SQL and database resources', '#336791', 'SQL', 'SQL', 'Guides and best practices for database management.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('HTML', 'HTML web development resources', '#E34F26', 'HTML', 'HTML', 'Basics and advanced topics in HTML development.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('CSS', 'CSS styling and frameworks', '#1572B6', 'CSS', 'CSS', 'Design and styling tips for modern web applications.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('React', 'React.js development resources', '#61DAFB', 'REACT', 'React', 'Frontend development with React library.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('Angular', 'Angular framework resources', '#DD0031', 'ANGULAR', 'Angular', 'Frontend development with Angular framework.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('Vue.js', 'Vue.js development resources', '#42B883', 'VUE', 'Vue.js', 'Frontend development with Vue.js framework.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category_tbl (name, description, color, "value", label, long_description, created_at, updated_at) VALUES ('DevOps', 'DevOps tools and practices', '#0A0A0A', 'DEVOPS', 'DevOps', 'CI/CD pipelines, automation, and cloud infrastructure.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Roles
INSERT INTO role_tbl (role_name) VALUES ('ROLE_USER');
INSERT INTO role_tbl (role_name) VALUES ('ROLE_ADMIN');

-- Usuarios
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('luis', 'luis@example.com', '$2a$10$SznJ5S3jwxY/w9psYKnucOM9KQ7i3oP3tooVarcAQhU4BXciO0iAe', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('ana', 'ana@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('carlos', 'carlos@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('maria', 'maria@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('jose', 'jose@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('laura', 'laura@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('andres', 'andres@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('sofia', 'sofia@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('fernando', 'fernando@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('valeria', 'valeria@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('valeria2', 'valeri2a@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Información de usuarios
INSERT INTO user_info_tbl (profile_picture, bio, last_login, is_active, phone, direction, user_id, name, lastname, work, education, pronouns, website, city, skills) VALUES ('pic_luis.png', 'Apasionado por la tecnologia y el cafe.', CURRENT_TIMESTAMP, TRUE, '555-1001', 'Calle 1 #123', 1, 'Luis', 'Martinez', 'Desarrollador Backend', 'Ingenieria en Sistemas', 'el', 'http://luis.dev', 'CDMX', 'Java, Spring Boot, SQL');
INSERT INTO user_info_tbl (profile_picture, bio, last_login, is_active, phone, direction, user_id, name, lastname, work, education, pronouns, website, city, skills) VALUES ('pic_ana.png', 'Amante del arte y la fotografía.', CURRENT_TIMESTAMP, TRUE, '555-1002', 'Avenida Reforma 45', 2, 'Ana', 'García', 'Fotógrafa', 'Lic. Artes Visuales', 'ella', 'http://ana.art', 'Monterrey', 'Fotografía, Edición');
INSERT INTO user_info_tbl (profile_picture, bio, last_login, is_active, phone, direction, user_id, name, lastname, work, education, pronouns, website, city, skills) VALUES ('pic_carlos.png', 'Ingeniero mecánico y ciclista aficionado.', CURRENT_TIMESTAMP, TRUE, '555-1003', 'Calle Los Pinos 89', 3, 'Carlos', 'Hernández', 'Ingeniero Mecánico', 'Ingeniería Mecánica', 'él', 'http://carlos.engineer', 'Guadalajara', 'AutoCAD, SolidWorks');
INSERT INTO user_info_tbl (profile_picture, bio, last_login, is_active, phone, direction, user_id, name, lastname, work, education, pronouns, website, city, skills) VALUES ('pic_maria.png', 'Chef apasionada por la repostería.', CURRENT_TIMESTAMP, TRUE, '555-1004', 'Boulevard del Sol 23', 4, 'María', 'Fernández', 'Chef', 'Gastronomía', 'ella', 'http://maria.cooking', 'Puebla', 'Repostería, Cocina Internacional');
INSERT INTO user_info_tbl (profile_picture, bio, last_login, is_active, phone, direction, user_id, name, lastname, work, education, pronouns, website, city, skills) VALUES ('pic_jose.png', 'Amante de los deportes y el trekking.', CURRENT_TIMESTAMP, TRUE, '555-1005', 'Calle Montaña 56', 5, 'José', 'Ramírez', 'Profesor de Educación Física', 'Lic. Educación', 'él', 'http://jose.sports', 'Toluca', 'Fútbol, Trekking, Fitness');
INSERT INTO user_info_tbl (profile_picture, bio, last_login, is_active, phone, direction, user_id, name, lastname, work, education, pronouns, website, city, skills) VALUES ('pic_laura.png', 'Diseñadora gráfica y amante del cine.', CURRENT_TIMESTAMP, TRUE, '555-1006', 'Calle Creativa 78', 6, 'Laura', 'Santos', 'Diseñadora Gráfica', 'Diseño Gráfico', 'ella', 'http://laura.design', 'Querétaro', 'Photoshop, Illustrator, Animación');
INSERT INTO user_info_tbl (profile_picture, bio, last_login, is_active, phone, direction, user_id, name, lastname, work, education, pronouns, website, city, skills) VALUES ('pic_andres.png', 'Desarrollador fullstack y gamer.', CURRENT_TIMESTAMP, TRUE, '555-1007', 'Avenida Código 99', 7, 'Andrés', 'Pérez', 'Desarrollador Fullstack', 'Ingeniería en Sistemas', 'él', 'http://andres.dev', 'Monterrey', 'Java, JS, React, Spring');
INSERT INTO user_info_tbl (profile_picture, bio, last_login, is_active, phone, direction, user_id, name, lastname, work, education, pronouns, website, city, skills) VALUES ('pic_sofia.png', 'Periodista y escritora de novelas cortas.', CURRENT_TIMESTAMP, TRUE, '555-1008', 'Calle Letras 12', 8, 'Sofía', 'Lopez', 'Periodista', 'Comunicación', 'ella', 'http://sofia.news', 'Guadalajara', 'Redacción, Investigación');
INSERT INTO user_info_tbl (profile_picture, bio, last_login, is_active, phone, direction, user_id, name, lastname, work, education, pronouns, website, city, skills) VALUES ('pic_fernando.png', 'Fotógrafo y viajero empedernido.', CURRENT_TIMESTAMP, TRUE, '555-1009', 'Avenida Mundo 34', 9, 'Fernando', 'Castillo', 'Fotógrafo', 'Fotografía', 'él', 'http://fernando.photos', 'Puebla', 'Fotografía, Edición, Viajes');
INSERT INTO user_info_tbl (profile_picture, bio, last_login, is_active, phone, direction, user_id, name, lastname, work, education, pronouns, website, city, skills) VALUES ('pic_valeria.png', 'Ingeniera de software y aficionada a la lectura.', CURRENT_TIMESTAMP, TRUE, '555-1010', 'Calle Código 101', 10, 'Valeria', 'Gómez', 'Ingeniera de Software', 'Ingeniería en Sistemas', 'ella', 'http://valeria.dev', 'Ciudad de México', 'Java, Python, SQL');

-- Asignación de rol USER_ROLE (id=1)
INSERT INTO user_role_tbl (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role_tbl (user_id, role_id) VALUES (2, 1);
INSERT INTO user_role_tbl (user_id, role_id) VALUES (3, 1);
INSERT INTO user_role_tbl (user_id, role_id) VALUES (4, 1);
INSERT INTO user_role_tbl (user_id, role_id) VALUES (5, 1);
INSERT INTO user_role_tbl (user_id, role_id) VALUES (6, 1);
INSERT INTO user_role_tbl (user_id, role_id) VALUES (7, 1);
INSERT INTO user_role_tbl (user_id, role_id) VALUES (8, 1);
INSERT INTO user_role_tbl (user_id, role_id) VALUES (9, 1);
INSERT INTO user_role_tbl (user_id, role_id) VALUES (10, 1);

-- user1 sigue Tech, Gaming y Programming
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (1, 1, NOW());
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (1, 9, NOW());
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (1, 20, NOW());

-- user2 sigue Health y Sports
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (2, 2, NOW());
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (2, 3, NOW());

-- user3 sigue Tech y Food
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (3, 1, NOW());
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (3, 4, NOW());

-- user4 sigue Travel, Music y Movies
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (4, 5, NOW());
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (4, 6, NOW());
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (4, 7, NOW());

-- user5 sigue Science y Gaming
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (5, 8, NOW());
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (5, 9, NOW());

-- user6 sigue Business y Education
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (6, 10, NOW());
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (6, 11, NOW());

-- user7 sigue History y Art
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (7, 12, NOW());
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (7, 13, NOW());

-- user8 sigue Politics y Nature
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (8, 14, NOW());
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (8, 15, NOW());

-- user9 sigue Books y Fashion
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (9, 16, NOW());
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (9, 17, NOW());

-- user10 sigue Photography, Cars y Programming
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (10, 18, NOW());
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (10, 19, NOW());
INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (10, 20, NOW());


-- Blogs
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Tech Trends 2025', 'Latest trends in technology', 'Content about tech trends...', 'PUBLISHED', 'tech-trends-2025', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Healthy Living Tips', 'How to maintain a healthy lifestyle', 'Content about health...', 'PUBLISHED', 'healthy-living-tips', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Lifestyle Hacks', 'Tips to improve daily life', 'Content about lifestyle...', 'PUBLISHED', 'lifestyle-hacks', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Education Resources', 'Learning resources and guides', 'Content about education...', 'PUBLISHED', 'education-resources', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Travel Asia', 'Exploring Asia destinations', 'Content about Asia travel...', 'PUBLISHED', 'travel-asia', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Programming Languages', 'Overview of popular programming languages', 'Content about programming...', 'PUBLISHED', 'programming-languages', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Java Spring Boot', 'Guide to Spring Boot development', 'Content about Spring Boot...', 'PUBLISHED', 'java-spring-boot', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Travel Europe', 'Exploring Europe destinations', 'Content about Europe travel...', 'PUBLISHED', 'travel-europe', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Fitness Routines', 'Effective fitness routines', 'Content about fitness...', 'PUBLISHED', 'fitness-routines', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('AI in 2025', 'Artificial Intelligence developments', 'Content about AI...', 'PUBLISHED', 'ai-in-2025', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Cybersecurity Basics', 'Introduction to cybersecurity', 'Content about cybersecurity...', 'PUBLISHED', 'cybersecurity-basics', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 9);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Cooking Tips', 'Tips for better cooking', 'Content about cooking...', 'PUBLISHED', 'cooking-tips', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Web Development', 'Modern web development practices', 'Content about web dev...', 'PUBLISHED', 'web-development', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Travel South America', 'Exploring South America', 'Content about South America...', 'PUBLISHED', 'travel-south-america', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Mental Health', 'Strategies for mental wellness', 'Content about mental health...', 'PUBLISHED', 'mental-health', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Photography Tips', 'Improve your photography skills', 'Content about photography...', 'PUBLISHED', 'photography-tips', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Blockchain Basics', 'Understanding blockchain technology', 'Content about blockchain...', 'PUBLISHED', 'blockchain-basics', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Mobile App Development', 'Creating apps for Android and iOS', 'Content about mobile apps...', 'PUBLISHED', 'mobile-app-development', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Travel North America', 'Exploring North America', 'Content about North America...', 'PUBLISHED', 'travel-north-america', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8);
INSERT INTO blog_tbl (title, description, content, status, slug, created_at, updated_at, user_id) VALUES ('Data Science 101', 'Introduction to data science', 'Content about data science...', 'PUBLISHED', 'data-science-101', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 9);


-- Blog - Categorías
-- Blog 1 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (1, 1);
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (1, 4);
-- Blog 2 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (2, 2);
-- Blog 3
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (3, 3);
-- Blog 4 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (4, 4);
-- Blog 5 -
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (5, 5);
-- Blog 6 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (6, 1);
-- Blog 7
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (7, 1);
-- Blog 8 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (8, 5);
-- Blog 9 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (9, 2);
-- Blog 10 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (10, 1);
-- Blog 11 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (11, 1);
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (11, 6);
-- Blog 12 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (12, 3);
-- Blog 13 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (13, 1);
-- Blog 14 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (14, 5);
-- Blog 15 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (15, 2);
-- Blog 16 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (16, 3);
-- Blog 17 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (17, 1);
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (17, 4);
-- Blog 18 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (18, 1);
-- Blog 19 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (19, 5);
-- Blog 20 
INSERT INTO blog_category_tbl (blog_id, category_id) VALUES (20, 4);

-- Usuario 1 leyó 10 blogs
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (1, 1, '2025-08-01 10:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (1, 2, '2025-08-01 11:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (1, 3, '2025-08-01 12:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (1, 4, '2025-08-01 13:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (1, 5, '2025-08-01 14:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (1, 6, '2025-08-01 15:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (1, 7, '2025-08-01 16:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (1, 8, '2025-08-01 17:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (1, 9, '2025-08-01 18:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (1, 10, '2025-08-01 19:00:00');

-- Usuario 2 leyó 10 blogs
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (2, 11, '2025-08-02 10:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (2, 12, '2025-08-02 11:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (2, 13, '2025-08-02 12:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (2, 14, '2025-08-02 13:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (2, 15, '2025-08-02 14:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (2, 16, '2025-08-02 15:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (2, 17, '2025-08-02 16:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (2, 18, '2025-08-02 17:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (2, 19, '2025-08-02 18:00:00');
INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (2, 20, '2025-08-02 19:00:00');

-- Usuario 1 dio like a 10 blogs
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (1, 1, '2025-08-05 10:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (1, 2, '2025-08-05 11:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (1, 3, '2025-08-05 12:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (1, 4, '2025-08-05 13:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (1, 5, '2025-08-05 14:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (1, 6, '2025-08-05 15:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (1, 7, '2025-08-05 16:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (1, 8, '2025-08-05 17:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (1, 9, '2025-08-05 18:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (1, 10, '2025-08-05 19:00:00');

-- Usuario 2 dio like a 10 blogs
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (2, 11, '2025-08-06 10:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (2, 12, '2025-08-06 11:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (2, 13, '2025-08-06 12:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (2, 14, '2025-08-06 13:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (2, 15, '2025-08-06 14:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (2, 16, '2025-08-06 15:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (2, 17, '2025-08-06 16:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (2, 18, '2025-08-06 17:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (2, 19, '2025-08-06 18:00:00');
INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (2, 20, '2025-08-06 19:00:00');

-- FOLLOWERS
-- Usuario 1 sigue a 2,5,7
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (1, 2, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (1, 5, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (1, 7, NOW());

-- Usuario 2 sigue a 1,3,6
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (2, 1, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (2, 3, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (2, 6, NOW());

-- Usuario 3 sigue a 2,4,8
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (3, 2, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (3, 4, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (3, 8, NOW());

-- Usuario 4 sigue a 1,5,9
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (4, 1, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (4, 5, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (4, 9, NOW());

-- Usuario 5 sigue a 3,6,10
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (5, 3, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (5, 6, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (5, 10, NOW());

-- Usuario 6 sigue a 1,4,7
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (6, 1, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (6, 4, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (6, 7, NOW());

-- Usuario 7 sigue a 2,5,9
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (7, 2, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (7, 5, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (7, 9, NOW());

-- Usuario 8 sigue a 3,6,10
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (8, 3, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (8, 6, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (8, 10, NOW());

-- Usuario 9 sigue a 1,4,8
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (9, 1, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (9, 4, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (9, 8, NOW());

-- Usuario 10 sigue a 2,5,7
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (10, 2, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (10, 5, NOW());
INSERT INTO user_follows_tbl (follower_id, followed_id, created_at) VALUES (10, 7, NOW());


-- COMMENTS
-- Comentarios para blogs del 1 al 20
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Great post! Very informative.', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('I learned a lot, thanks!', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Interesting perspective.', 3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Nice article!', 4, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('I disagree with some points.', 5, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Thanks for sharing!', 6, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Helpful content.', 7, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('I will try this out.', 8, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Good read!', 9, 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Amazing insights.', 10, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Loved this post!', 1, 11, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Very useful information.', 2, 12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Thanks for the tips.', 3, 13, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Awesome read.', 4, 14, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('I appreciate this!', 5, 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Very interesting.', 6, 16, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Helpful article.', 7, 17, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Good tips.', 8, 18, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('Well explained.', 9, 19, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO comment_tbl (content, user_id, blog_id, created_at, updated_at) VALUES ('I will share this.', 10, 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- REPLIES
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 1', 1, 1, 1, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 1', 2, 1, 1, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 2', 3, 2, 2, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 2', 4, 2, 2, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 3', 5, 3, 3, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 3', 6, 3, 3, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 4', 7, 4, 4, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 4', 8, 4, 4, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 5', 9, 5, 5, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 5', 10, 5, 5, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 6', 1, 6, 6, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 6', 2, 6, 6, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 7', 3, 7, 7, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 7', 4, 7, 7, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 8', 5, 8, 8, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 8', 6, 8, 8, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 9', 7, 9, 9, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 9', 8, 9, 9, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 10', 9, 10, 10, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 10', 10, 10, 10, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 11', 1, 11, 11, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 11', 2, 11, 11, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 12', 3, 12, 12, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 12', 4, 12, 12, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 13', 5, 13, 13, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 13', 6, 13, 13, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 14', 7, 14, 14, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 14', 8, 14, 14, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 15', 9, 15, 15, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 15', 10, 15, 15, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 16', 1, 16, 16, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 16', 2, 16, 16, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 17', 3, 17, 17, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 17', 4, 17, 17, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 18', 5, 18, 18, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 18', 6, 18, 18, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 19', 7, 19, 19, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 19', 8, 19, 19, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 1 al comentario 20', 9, 20, 20, NOW(), NOW());
INSERT INTO reply_tbl (content, user_id, blog_id, comment_id, created_at, updated_at) VALUES ('Reply 2 al comentario 20', 10, 20, 20, NOW(), NOW());


