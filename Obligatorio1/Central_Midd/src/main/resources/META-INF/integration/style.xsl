<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" >
<xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
<xsl:template match="/">
idCliente, codMoneda, importe, fecha , hora 
<xsl:for-each select="//PagarOffline">

         
<xsl:value-of select="concat(arg0,',',arg1,',',arg2,',',substring(arg3,1,10),',',substring(arg3,11,16),'&#xA;')"/>
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>