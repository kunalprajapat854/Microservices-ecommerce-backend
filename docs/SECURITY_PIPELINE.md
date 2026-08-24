# DevSecOps CI/CD Pipeline

This repo runs a security-integrated pipeline on every push/PR to `main`
via [`.github/workflows/devsecops.yml`](../.github/workflows/devsecops.yml).

## Stages

| Stage | Tool | Blocks merge? |
|---|---|---|
| Secrets detection | Gitleaks (`gitleaks/gitleaks-action`) | Yes — fails on any finding |
| Build & unit test | Maven (per service, matrix) | Yes — build must compile & pass tests |
| SAST | SonarQube/SonarCloud (`sonar-maven-plugin`, quality gate) | Yes — `sonar.qualitygate.wait=true` |
| Container build | `docker build` (per service) | N/A (prerequisite for scan) |
| Container vulnerability scan | Trivy (`aquasecurity/trivy-action`) | Yes — CRITICAL severity fails the job |
| Deploy | `deploy-gate` job | Only runs if gitleaks + sonarqube + trivy-scan all pass |

## One-time setup required

1. **SonarCloud**
   - Sign in at [sonarcloud.io](https://sonarcloud.io) with GitHub, import the
     `kunalprajapat854/Microservices-ecommerce-backend` repo, create the
     organization key `kunalprajapat854` (or update the `sonar.organization`
     property in each `pom.xml` to match whatever key SonarCloud assigns you).
   - Create one project per service with keys matching each pom's
     `sonar.projectKey` (`kunalprajapat854_<service>`), or let "Analyze new
     project" auto-create them on first scan.
   - Generate a token: My Account → Security → Generate Token.
   - Add it as a repo secret: Settings → Secrets and variables → Actions →
     New repository secret → name `SONAR_TOKEN`.
   - Set each project to **CI-based analysis** (not automatic analysis) so
     the quality gate reflects the GitHub Actions scan.
2. **Trivy** — no account needed, runs self-contained via the action.
3. **Gitleaks** — no account needed for the OSS action; `GITHUB_TOKEN` is auto-provided.
4. **Branch protection** — Settings → Branches → protect `main`, require the
   `gitleaks`, `build-test`, `sonarqube`, `trivy-scan` checks to pass before merge.

## Audit trail

- Trivy SARIF results are uploaded to the **Security → Code scanning** tab per
  service (`trivy-<service>` category) and archived as workflow artifacts for
  90 days — gives per-commit vulnerability history without extra tooling.
- SonarQube keeps its own historical dashboard per project (findings, quality
  gate history, new-code issues over time).
- Gitleaks findings show in the Action run summary/logs for that commit.

## Remediation workflow

1. A failed Trivy/SonarQube/Gitleaks check blocks the PR from merging.
2. Triage the finding in the tool's own UI (Security tab / SonarQube dashboard).
3. Fix (update base image / dependency, patch code, rotate + remove leaked
   secret) and push — pipeline re-runs automatically.
4. If a finding is a false positive or accepted risk, record it explicitly:
   - Trivy: add a `.trivyignore` entry with a comment explaining why.
   - SonarQube: mark issue "Won't Fix" / "False Positive" with a justification comment.
   - Gitleaks: add a scoped rule to `.gitleaks.toml`'s `[allowlist]` with a comment.
   Never silence a finding without a recorded reason — that comment *is* the audit trail.
