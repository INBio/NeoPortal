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
        <div class="container data_wrapper">
            <div id="menu-panel" class="left-column"></div>
            
            <div class="content-column">
                <div class="naturalHistory data-panel">
                    <h3>Natural History</h3>

                    <xsl:if test="reproduction != ''">
                        <h4>Reproduction:</h4>
                        <div>
                            <xsl:value-of select="reproduction" />
                        </div><!-- end reproducion div -->
                    </xsl:if>

                    <xsl:if test="annualCycle != ''">
                        <h4>Annual Cycle:</h4>
                        <div>
                            <xsl:value-of select="annualCycle" />
                        </div><!-- end annualCycle div -->
                    </xsl:if>

                    <xsl:if test="feeding != ''">
                        <h4>Feeding:</h4>
                        <div>
                            <xsl:value-of select="feeding" />
                        </div><!-- end feeding div -->
                    </xsl:if>

                    <xsl:if test="behavior != ''">
                        <h4>Behavior:</h4>
                        <div>
                            <xsl:value-of select="behavior" />
                        </div><!-- end behavior div -->
                    </xsl:if>

                    <xsl:if test="habit != ''">
                        <h4>Habit:</h4>
                        <div>
                            <xsl:value-of select="habit" />
                        </div>
                    </xsl:if>
                </div><!-- end naturalHistory div -->

                <div class="habitatDistribution data-panel">
                    <h3>Habitat and Distribution</h3>

                    <xsl:if test="habitat != ''">
                        <h4>Habitat:</h4>
                        <div>
                            <xsl:value-of select="habitat" />
                        </div>
                    </xsl:if>

                    <xsl:if test="distribution != ''">
                        <h4>Distribution:</h4>
                        <div>
                            <xsl:value-of select="distribution" />
                        </div>
                    </xsl:if>

                </div><!-- end habitatDistribution div -->

                <div class="demographyConservation data-panel">
                    <h3>Demography and Conservation</h3>

                    <xsl:if test="populationBiology != ''">
                        <h4>Population Biology:</h4>
                        <div>
                            <xsl:value-of select="populationBiology" />
                        </div>
                    </xsl:if>

                    <xsl:if test="threatStatus != ''">
                        <h4>Threat Status:</h4>
                        <div>
                            <xsl:value-of select="threatStatus" />
                        </div>
                    </xsl:if>

                </div><!-- end demographyConservation div -->

                <div class="usesManagement data-panel">
                    <h3>Uses and Management</h3>

                    <xsl:if test="theUses != ''">
                        <h4>Uses:</h4>
                        <div>
                            <xsl:value-of select="theUses" />
                        </div>
                    </xsl:if>

                    <xsl:if test="theManagement != ''">
                        <h4>Management:</h4>
                        <div>
                            <xsl:value-of select="theManagement" />
                        </div>
                    </xsl:if>

                </div><!-- end usesManagement div -->

                <div class="description data-panel">
                    <h3>Description</h3>

                    <xsl:if test="scientificDescription != ''">
                        <h4>Scientific Description:</h4>
                        <div>
                            <xsl:value-of select="scientificDescription" />
                        </div>
                    </xsl:if>

                </div><!-- end description div -->

                <div class="information data-panel">
                    <h3>Information</h3>

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

                <div class="documentation data-panel">
                    <h3>Documentation</h3>

                    <xsl:if test="theReferences != ''">
                        <h4>References:</h4>
                        <div>
                            <xsl:value-of select="theReferences" />
                        </div>
                    </xsl:if>

                </div><!-- end description div -->
            </div>
        </div>
        
    </xsl:template>

</xsl:stylesheet>
