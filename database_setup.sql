-- Create the database
CREATE DATABASE IF NOT EXISTS CompetitionDB;
USE CompetitionDB;

-- Create the Competitors table
CREATE TABLE IF NOT EXISTS Competitors (
    competitorId INT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    level VARCHAR(20) NOT NULL,
    country VARCHAR(50) NOT NULL,
    score1 INT NOT NULL,
    score2 INT NOT NULL,
    score3 INT NOT NULL,
    score4 INT NOT NULL,
    score5 INT NOT NULL,
    CONSTRAINT score1_range CHECK (score1 >= 0 AND score1 <= 5),
    CONSTRAINT score2_range CHECK (score2 >= 0 AND score2 <= 5),
    CONSTRAINT score3_range CHECK (score3 >= 0 AND score3 <= 5),
    CONSTRAINT score4_range CHECK (score4 >= 0 AND score4 <= 5),
    CONSTRAINT score5_range CHECK (score5 >= 0 AND score5 <= 5)
);

-- Insert some sample data
INSERT INTO Competitors VALUES
(200, 'Alice', 'Green', 'BEGINNER', 'UK', 4, 3, 5, 2, 4),
(201, 'Bob', 'Brown', 'INTERMEDIATE', 'USA', 3, 4, 4, 5, 4),
(202, 'Carol', 'White', 'ADVANCED', 'Canada', 5, 5, 4, 4, 5),
(203, 'David', 'Black', 'ADVANCED', 'Australia', 4, 4, 5, 5, 4);