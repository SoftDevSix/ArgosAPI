CREATE TABLE project (
    project_id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    required_coverage_percentage INT NOT NULL,
    required_code_rating VARCHAR(50) NOT NULL,
    status VARCHAR(50)
);

CREATE TABLE project_params (
    params_id UUID PRIMARY KEY,
    project_id UUID NOT NULL,
    required_coverage_percentage INT NOT NULL,
    required_code_rating VARCHAR(1) NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
);

CREATE TABLE project_results (
    results_id UUID PRIMARY KEY,
    project_id UUID NOT NULL,
    status VARCHAR(50) NOT NULL,
    actual_coverage INT,
    required_coverage INT,
    actual_rating VARCHAR(1),
    expected_rating VARCHAR(1),
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
);

CREATE TABLE file (
    file_id UUID PRIMARY KEY,
    project_id UUID NOT NULL,
    file_name VARCHAR(15) NOT NULL,
    path TEXT NOT NULL,
    code_lines INT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
);

CREATE TABLE file_coverage_result (
    file_coverage_id UUID PRIMARY KEY,
    file_id UUID NOT NULL,
    all_statements JSONB NOT NULL,
    FOREIGN KEY (file_id) REFERENCES file(file_id) ON DELETE CASCADE
);

CREATE TABLE method_coverage_result (
    method_coverage_id UUID PRIMARY KEY,
    file_coverage_id UUID NOT NULL,
    statements JSONB NOT NULL,
    FOREIGN KEY (file_coverage_id) REFERENCES file_coverage_result(file_coverage_id) ON DELETE CASCADE
);

CREATE TYPE project_status AS ENUM ('FAILED', 'PASSED');

ALTER TABLE project_results
ALTER COLUMN status TYPE project_status USING (status::project_status);

ALTER TABLE project
ALTER COLUMN status TYPE project_status USING (status::project_status);
