CREATE TABLE project (
    project_id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    project_params_id UUID,
    project_results_id UUID,
    CONSTRAINT fk_project_params
        FOREIGN KEY (project_params_id) REFERENCES project_params(project_params_id) ON DELETE CASCADE,
    CONSTRAINT fk_project_results
        FOREIGN KEY (project_results_id) REFERENCES project_results(project_results_id) ON DELETE CASCADE
);

CREATE TABLE project_params (
    project_params_id UUID PRIMARY KEY,
    required_coverage_percentage FLOAT NOT NULL,
    required_code_rating project_rating NOT NULL,
);

CREATE TABLE project_results (
    project_results_id UUID PRIMARY KEY,
    status project_status NOT NULL,
    project_coverage_result_id UUID,
    code_analysis_result_id UUID,
    CONSTRAINT fk_coverage_result
        FOREIGN KEY (project_coverage_result_id) REFERENCES project_coverage_result(project_coverage_result_id) ON DELETE CASCADE,
    CONSTRAINT fk_code_analysis_result
        FOREIGN KEY (code_analysis_result_id) REFERENCES code_analysis_result(code_analysis_result_id) ON DELETE CASCADE
);

CREATE TABLE project_coverage_result (
    project_coverage_result_id UUID PRIMARY KEY,
    total_coverage FLOAT NOT NULL
);

CREATE TABLE code_analysis_result (
    code_analysis_result_id UUID PRIMARY KEY,
    actual_rating VARCHAR(255) NOT NULL
);

CREATE TABLE file (
    file_id UUID PRIMARY KEY,
    file_name VARCHAR(15) NOT NULL,
    path TEXT NOT NULL,
    code_lines INT NOT NULL,
    project_id UUID,
    coverage_result_id UUID,
    CONSTRAINT fk_project
        FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE,
    CONSTRAINT fk_coverage_result
        FOREIGN KEY (coverage_result_id) REFERENCES file_coverage_result (file_coverage_id) ON DELETE CASCADE
);

CREATE TABLE file_coverage_result (
    file_coverage_id UUID PRIMARY KEY,
    coverage_percentage FLOAT NOT NULL,
    method_coverage_percentage FLOAT NOT NULL
);

CREATE TABLE method_coverage_result (
    method_coverage_id UUID PRIMARY KEY,
    coverage_percentage FLOAT NOT NULL,
    coverage_result_id UUID NOT NULL,
    CONSTRAINT fk_coverage_result
        FOREIGN KEY (coverage_result_id) REFERENCES file_coverage_result (file_coverage_id) ON DELETE CASCADE
);

CREATE TABLE method_statements (
    method_coverage_id UUID NOT NULL,
    key INT NOT NULL,
    value BOOLEAN NOT NULL,
    CONSTRAINT fk_method_coverage
        FOREIGN KEY (method_coverage_id) REFERENCES method_coverage_result (method_coverage_id) ON DELETE CASCADE
);

CREATE TYPE project_rating AS ENUM ('A', 'B', 'C', 'D');

CREATE TYPE project_status AS ENUM ('PASSED', 'FAILED');
