CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    position VARCHAR(255)
);

-- Sample data
INSERT INTO employees (name, position) VALUES ('John Doe', 'Manager');
INSERT INTO employees (name, position) VALUES ('Jane Smith', 'Developer');
