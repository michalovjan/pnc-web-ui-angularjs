/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * JPA entity representing the artifacts generated by a build (build output).  
 * Each artifact is mapped to a single build which produced the artifact.
 * 
 */
@Entity
@DiscriminatorValue(ArtifactType.IMPORTED)
public class ImportedArtifact extends Artifact {

    private static final long serialVersionUID = 1L;

    /**
     * The location from which this artifact was originally downloaded
     */
    @Column(unique=true, updatable=false)
    private String originUrl;

    /**
     * The date when this artifact was originally downloaded
     */
    @NotNull
    @Column(updatable=false)
    private Date downloadDate;

    private ImportedArtifact() {
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public Date getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(Date downloadDate) {
        this.downloadDate = downloadDate;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Imported Artifact [id: " + getId() + ", retrieved from:" + this.getOriginUrl() + "]";
    }

    public static class Builder {

        private Integer id;

        private String identifier;

        private RepositoryType repoType;

        private String checksum;

        private String filename;

        private String deployUrl;

        private Set<BuildRecord> dependantBuildRecords;

        private String originUrl;

        private Date downloadDate;

        public Builder() {
            dependantBuildRecords = new HashSet<BuildRecord>();
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public ImportedArtifact build() {
            ImportedArtifact artifact = new ImportedArtifact();
            artifact.setId(id);
            artifact.setIdentifier(identifier);
            artifact.setRepoType(repoType);
            artifact.setChecksum(checksum);
            artifact.setFilename(filename);
            artifact.setDeployUrl(deployUrl);
            if (dependantBuildRecords != null) {
                artifact.setDependantBuildRecords(dependantBuildRecords);
            }
            artifact.setOriginUrl(originUrl);
            artifact.setDownloadDate(downloadDate);

            return artifact;
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder repoType(RepositoryType repoType) {
            this.repoType = repoType;
            return this;
        }

        public Builder checksum(String checksum) {
            this.checksum = checksum;
            return this;
        }

        public Builder filename(String filename) {
            this.filename = filename;
            return this;
        }

        public Builder deployUrl(String deployUrl) {
            this.deployUrl = deployUrl;
            return this;
        }

        public Builder dependantBuildRecords(Set<BuildRecord> dependantBuildRecords) {
            this.dependantBuildRecords = dependantBuildRecords;
            return this;
        }

        public Builder originUrl(String originUrl) {
            this.originUrl = originUrl;
            return this;
        }

        public Builder downloadDate(Date downloadDate) {
            this.downloadDate = downloadDate;
            return this;
        }

    }
}
