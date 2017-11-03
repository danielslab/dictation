UPDATE questions
SET answer_text = 'I''m so glad you all could make it tonight to celebrate our company''s expansion.' || CHAR(13) || CHAR(10) || 'When I first started this greeting card company, it was only a small team of three people struggling to make our business work.' || CHAR(13) || CHAR(10) || 'And now we have twenty full-time employees, we''ve expanded our distribution, and we even have a new, much larger office space.' || CHAR(13) || CHAR(10) || 'So please, before you leave the banquet, pick up your gift bag.' || CHAR(13) || CHAR(10) || 'It''s my personal token of appreciation to thank all of you for your contribution to our company''s success.'
WHERE id = 104

UPDATE questions
SET answer_text = 'Hi, this is Laura Dunn.' || CHAR(13) || CHAR(10) || 'I''m calling about a catering order you just delivered for our corporate luncheon this afternoon.' || CHAR(13) || CHAR(10) || 'I just realized that one of the food trays we ordered wasn''t included we''re missing the pasta.' || CHAR(13) || CHAR(10) || 'Now, between the sushi and the chicken dish, we''ll have enough food, but I wanted to make sure we get a refund on the pasta.' || CHAR(13) || CHAR(10) || 'Could you please process the refund to the same credit card I used to place the order?' || CHAR(13) || CHAR(10) || 'Thanks and have a nice day.'
WHERE id = 106
