CREATE TABLE users
(
  id         VARCHAR(48) PRIMARY KEY,
  full_name  VARCHAR(255)             NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE urls_shortened
(
  id           VARCHAR(50) PRIMARY KEY,
  user_id      VARCHAR(48)                   NOT NULL REFERENCES users (id),
  is_private   BOOLEAN                  NOT NULL DEFAULT FALSE,
  original_url TEXT                     NOT NULL,
  expires_at   TIMESTAMP WITH TIME ZONE,
  created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);
