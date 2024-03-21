DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS teams CASCADE;
DROP TABLE IF EXISTS memberboads CASCADE;
DROP TABLE IF EXISTS matchboards CASCADE;
DROP TABLE IF EXISTS memberforms CASCADE;
DROP TABLE IF EXISTS matchforms CASCADE;
DROP TABLE IF EXISTS users_teams CASCADE;

CREATE TABLE IF NOT EXISTS users (
  user_id SERIAL NOT NULL,
  authority VARCHAR(10) NOT NULL,
  name VARCHAR(10) NOT NULL,
  age INT NOT NULL,
  birth_date DATE NOT NULL,
  prefecture VARCHAR(5) NOT NULL,
  city VARCHAR(10) NOT NULL,
  username VARCHAR(30) NOT NULL,
  password VARCHAR(255) NOT NULL,
  picture VARCHAR(100) ,
  position VARCHAR(30) NOT NULL,
  experience VARCHAR(10) NOT NULL,
  throwing VARCHAR(3) NOT NULL,
  batting VARCHAR(3) NOT NULL,
  introduction VARCHAR(1000) NOT NULL,
  tel VARCHAR(15) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS teams (
  id SERIAL NOT NULL,
  name VARCHAR(15) NOT NULL,
  read VARCHAR(15) NOT NULL,
  prefecture VARCHAR(5) NOT NULL,
  erea VARCHAR(10) NOT NULL,
  exeperience VARCHAR(10) NOT NULL,
  frequency VARCHAR(10) NOT NULL,
  activity_days VARCHAR(10) NOT NULL,
  match_days VARCHAR(10) NOT NULL,
  team_introduction VARCHAR(1000) NOT NULL,
  formation DATE NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL, 
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS memberboads (
  id SERIAL NOT NULL,
  title VARCHAR(30) NOT NULL,
  apply int NOT NULL,
  newbieposition VARCHAR(20) NOT NULL,
  age_min int NOT NULL,
  age_max int NOT NULL,
  newplayer VARCHAR(10) NOT NULL, 
  team_pr VARCHAR(1000) NOT NULL, 
  apply_end VARCHAR(10) NOT NULL, 
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL, 
  PRIMARY KEY (id)
);
  
CREATE TABLE IF NOT EXISTS matchboads (
  id SERIAL NOT NULL,
  dateandtime VARCHAR(20) NOT NULL,
  title VARCHAR(30) NOT NULL,
  ground VARCHAR(30) NOT NULL,
  referee VARCHAR(3) NOT NULL,
  cost int NOT NULL,
  helpmember VARCHAR(10) NOT NULL, 
  comment VARCHAR(1000) NOT NULL, 
  apply_end VARCHAR(10) NOT NULL, 
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL, 
  PRIMARY KEY (id)
);
  
CREATE TABLE IF NOT EXISTS memberforms (
  id SERIAL NOT NULL,
  messsage VARCHAR(1000) NOT NULL, 
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL, 
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS matchforms (
  id SERIAL NOT NULL,
  messsage VARCHAR(1000) NOT NULL, 
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL, 
  PRIMARY KEY (id)
);


