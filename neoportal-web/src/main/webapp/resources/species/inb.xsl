<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : inb.xsl
    Created on : 17 de mayo de 2011, 03:58 PM
    Author     : avargas
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="taxon-description/element">
        <h1><xsl:value-of select="scientificName" /></h1>
        <div class="naturalHistory">
            <h2>Natural History</h2>
            
            <xsl:if test="habit != ''">
                <h3>Habit:</h3>
                <div>
                    <xsl:value-of select="habit" />
                </div>
            </xsl:if>
            
            <xsl:if test="lifeCycle != ''">
                <h3>Life Cycle:</h3>
                <div>
                    <xsl:value-of select="lifeCycle" />
                </div><!-- end lifeCycle div -->
            </xsl:if>
            
            <xsl:if test="reproduction != ''">
                <h3>Reproduction:</h3>
                <div>
                    <xsl:value-of select="reproduction" />
                </div><!-- end reproducion div -->
            </xsl:if>
            
            <xsl:if test="annualCycle != ''">
                <h3>Annual Cycle:</h3>
                <div>
                    <xsl:value-of select="annualCycle" />
                </div><!-- end annualCycle div -->
            </xsl:if>
            
            <xsl:if test="feeding != ''">
                <h3>Feeding:</h3>
                <div>
                    <xsl:value-of select="feeding" />
                </div><!-- end feeding div -->
            </xsl:if>
            
            <xsl:if test="behavior != ''">
                <h3>Behavior:</h3>
                <div>
                    <xsl:value-of select="behavior" />
                </div><!-- end behavior div -->
            </xsl:if>
            
            <xsl:if test="interactions != ''">
                <h3>Interactions:</h3>
                <div>
                    <xsl:value-of select="interactions" />
                </div><!-- end behavior div -->
            </xsl:if>
            
            <xsl:if test="molecularData != ''">
                <h3>Molecular Data:</h3>
                <div>
                    <xsl:value-of select="molecularData" />
                </div><!-- end behavior div -->
            </xsl:if>

        </div><!-- end naturalHistory div -->
        
        <div class="habitatDistribution">
            <h2>Habitat and Distribution</h2>
            
            <xsl:if test="habitat != ''">
                <h3>Habitat:</h3>
                <div>
                    <xsl:value-of select="habitat" />
                </div>
            </xsl:if>
            
            <xsl:if test="distribution != ''">
                <h3>Distribution:</h3>
                <div>
                    <xsl:value-of select="distribution" />
                </div>
            </xsl:if>
            
        </div><!-- end habitatDistribution div -->
        
        <div class="usesManagement">
            <h2>Uses and Management</h2>
            
            <xsl:if test="theUses != ''">
                <h3>Uses:</h3>
                <div>
                    <xsl:value-of select="theUses" />
                </div>
            </xsl:if>
            
            <xsl:if test="theManagement != ''">
                <h3>Management:</h3>
                <div>
                    <xsl:value-of select="theManagement" />
                </div>
            </xsl:if>
            
        </div><!-- end usesManagement div -->
        
        <div class="demographyConservation">
            <h2>Demography and Conservation</h2>
            
            <xsl:if test="populationBiology != ''">
                <h3>Population Biology:</h3>
                <div>
                    <xsl:value-of select="populationBiology" />
                </div>
            </xsl:if>
            
            <xsl:if test="threatStatus != ''">
                <h3>Threat Status:</h3>
                <div>
                    <xsl:value-of select="threatStatus" />
                </div>
            </xsl:if>
            
            <xsl:if test="territory != ''">
                <h3>Territory:</h3>
                <div>
                    <xsl:value-of select="territory" />
                </div>
            </xsl:if>
                       
        </div><!-- end demographyConservation div -->
        
        <div class="description">
            <h2>Description</h2>
            
            <xsl:if test="scientificName != ''">
                <h3>Scientific Name:</h3>
                <div>
                    <xsl:value-of select="scientificName" />
                </div>
            </xsl:if>
                       
        </div><!-- end description div -->
        
        <div class="information">
            <h2>Information</h2>
            
            <xsl:if test="language != ''">
                <p>
                    <span>Language: </span>
                    <xsl:value-of select="language" />
                </p>
            </xsl:if>
            
            <xsl:if test="creators != ''">
                <p>
                    <span>Author: </span>
                    <xsl:value-of select="creators" />
                </p>
            </xsl:if>
            
            <xsl:if test="contributors != ''">
                <p>
                    <span>Collaborators: </span>
                    <xsl:value-of select="contributors" />
                </p>
            </xsl:if>
            
            <xsl:if test="taxonRecordId != ''">
                <p>
                    <span>Species Record ID: </span>
                    <xsl:value-of select="taxonRecordId" />
                </p>
            </xsl:if>
            
            <xsl:if test="sinonyms != ''">
                <p>
                    <span>Sinonyms: </span>
                    <xsl:value-of select="synonyms" />
                </p>
            </xsl:if>
            
            <xsl:if test="dateCreated != ''">
                <p>
                    <span>Date Created: </span>
                    <xsl:value-of select="dateCreated" />
                </p>
            </xsl:if>
            
            <xsl:if test="dateLastModified != ''">
                <p>
                    <span>Last Modification Date: </span>
                    <xsl:value-of select="dateLastModified" />
                </p>
            </xsl:if>
            
        </div><!-- end description div -->
        
        <div class="documentation">
            <h2>Documentation</h2>
            
            <xsl:if test="theReferences != ''">
                <h3>References:</h3>
                <div>
                    <xsl:value-of select="theReferences" />
                </div>
            </xsl:if>

        </div><!-- end description div -->
        
    </xsl:template>

</xsl:stylesheet>
