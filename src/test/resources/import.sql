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
INSERT INTO role_tbl (role_name) VALUES ('USER_ROLE');
INSERT INTO role_tbl (role_name) VALUES ('ADMIN_ROLE');

-- Usuarios
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('luis', 'luis@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('ana', 'ana@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('carlos', 'carlos@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('maria', 'maria@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('jose', 'jose@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('laura', 'laura@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('andres', 'andres@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('sofia', 'sofia@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('fernando', 'fernando@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_tbl (username, email, password, created_at, update_at) VALUES ('valeria', 'valeria@example.com', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Información de usuarios
INSERT INTO user_info_tbl (profile_picture, bio, last_login, is_active, phone, direction, user_id, name, lastname, work, education, pronouns, website, city, skills) VALUES ('pic_luis.png', 'Apasionado por la tecnología y el café.', CURRENT_TIMESTAMP, TRUE, '555-1001', 'Calle 1 #123', 1, 'Luis', 'Martínez', 'Desarrollador Backend', 'Ingeniería en Sistemas', 'él', 'http://luis.dev', 'Ciudad de México', 'Java, Spring Boot, SQL');
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

