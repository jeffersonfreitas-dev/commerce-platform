<#import "template.ftl" as layout>

<@layout.registrationLayout displayMessage=false ; section>

<#if section = "header">

    <div class="ecom-header ecom-header--error">
        <div class="ecom-error-icon" aria-hidden="true">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round">
                <circle cx="12" cy="12" r="10"/>
                <line x1="12" y1="8" x2="12" y2="12"/>
                <line x1="12" y1="16" x2="12.01" y2="16"/>
            </svg>
        </div>
        <h1 class="ecom-title">Algo deu errado</h1>
        <p class="ecom-subtitle">
            ${kcSanitize(message.summary)?no_esc}
        </p>
    </div>

<#elseif section = "form">

    <#if skipLink??>
        <div class="ecom-actions">
            <a href="${client.baseUrl!'#'}" class="ecom-button">
                Voltar ao site
            </a>
        </div>
    <#else>
        <div class="ecom-actions">
            <a href="${url.loginUrl}" class="ecom-button">
                Ir para o login
            </a>
        </div>
    </#if>

</#if>

</@layout.registrationLayout>