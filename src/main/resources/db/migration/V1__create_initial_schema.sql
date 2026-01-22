CREATE TABLE users
(
  id         BIGSERIAL PRIMARY KEY,
  full_name  VARCHAR(255)             NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE urls_shortened
(
  id           VARCHAR(50) PRIMARY KEY,
  user_id      BIGINT                   NOT NULL REFERENCES users (id),
  is_private   BOOLEAN                  NOT NULL DEFAULT FALSE,
  original_url TEXT                     NOT NULL,
  expires_at   TIMESTAMP WITH TIME ZONE,
  created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_urls_user_id ON urls_shortened (user_id);
