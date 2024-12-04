INSERT INTO
    project_params (project_params_id, required_coverage_percentage, required_code_rating)
VALUES
    ('3e583c6d-999c-4864-944e-6db94cf5b153', 85, 'A'),
    ('f0217cd5-4ffe-48c5-a7fc-cbe9edb44f2e', 90, 'B');

INSERT INTO project_coverage_result (project_coverage_result_id, total_coverage)
VALUES
    ('7b4c391d-81cd-49d8-8754-0c5af2e72c65', 88.0),
    ('9f3176a3-27e8-40cc-b10a-c8b1d737d5fa', 80.0);

INSERT INTO code_analysis_result (code_analysis_result_id, actual_rating)
VALUES
    ('8c7abef2-b8c4-4fdc-87ff-471d7609d2ae', 'A'),
    ('5c9d8e36-d991-4e58-b4f0-30e638c601db', 'B');

INSERT INTO code_analysis_result (code_analysis_result_id, actual_rating)
VALUES
    ('8c7abef2-b8c4-4fdc-87ff-471d7609d2ae', 'A'),
    ('5c9d8e36-d991-4e58-b4f0-30e638c601db', 'B');

INSERT INTO project_results (project_results_id, status, project_coverage_result_id, code_analysis_result_id)
VALUES
    ('baf6d674-81c4-429f-9664-839538a290f8', 'PASSED',
     '7b4c391d-81cd-49d8-8754-0c5af2e72c65', '8c7abef2-b8c4-4fdc-87ff-471d7609d2ae'),
    ('36feb4bf-5885-40f1-98e1-29d03d7e7b4b', 'FAILED',
     '9f3176a3-27e8-40cc-b10a-c8b1d737d5fa', '5c9d8e36-d991-4e58-b4f0-30e638c601db');

INSERT INTO project (project_id, name, description, project_params_id, project_results_id)
VALUES
    ('525dbe0d-4987-4304-b39d-43840f4c18ee', 'Project Alpha', 'Description for Project Alpha',
     '3e583c6d-999c-4864-944e-6db94cf5b153', 'baf6d674-81c4-429f-9664-839538a290f8'),
    ('f69e7e4d-47f9-4e08-86e1-667f558051bd', 'Project Beta', 'Description for Project Beta',
     'f0217cd5-4ffe-48c5-a7fc-cbe9edb44f2e', '36feb4bf-5885-40f1-98e1-29d03d7e7b4b');

INSERT INTO file (file_id, file_name, path, code_lines, project_id, coverage_result_id)
VALUES
    ('e49d9b78-c422-42ed-8c16-9c86dde9e558', 'Main.java', '/src/main/java/Main.java', 120,
     '525dbe0d-4987-4304-b39d-43840f4c18ee', '9ce01882-7752-459f-8477-bd92918673d2'),
    ('31dc5ee2-f404-458c-a771-d76245692f34', 'App.java', '/src/app/App.js', 85,
     'f69e7e4d-47f9-4e08-86e1-667f558051bd', '16dbab3c-38dd-4ff7-8126-81b44685aceb');

INSERT INTO file_coverage_result (file_coverage_id, coverage_percentage, method_coverage_percentage)
VALUES
    ('9ce01882-7752-459f-8477-bd92918673d2', 95.0, 90.0),
    ('16dbab3c-38dd-4ff7-8126-81b44685aceb', 98.0, 94.0);

INSERT INTO method_coverage_result (method_coverage_id, coverage_percentage, coverage_result_id)
VALUES
    ('09564cc6-75ab-46be-8d1d-382586653ace', 85.0, '9ce01882-7752-459f-8477-bd92918673d2'),
    ('47a5d99d-d044-4056-b812-7001c3c67005', 95.0, '16dbab3c-38dd-4ff7-8126-81b44685aceb');

INSERT INTO method_statements (method_coverage_id, key, value)
VALUES
    ('09564cc6-75ab-46be-8d1d-382586653ace', 1, true),
    ('09564cc6-75ab-46be-8d1d-382586653ace', 2, false),
    ('47a5d99d-d044-4056-b812-7001c3c67005', 1, true),
    ('47a5d99d-d044-4056-b812-7001c3c67005', 2, true);
