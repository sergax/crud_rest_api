ALTER TABLE event
ADD FOREIGN KEY (user_id) REFERENCES user(user_id);

ALTER TABLE event
ADD FOREIGN KEY (event_id) REFERENCES event(event_id);