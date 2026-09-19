<#import "template.ftl" as layout>

<@layout.registrationLayout displayMessage=false ; section>

<#if section = "header">

    <div class="ecom-header">
        <div class="ecom-error-icon" aria-hidden="true">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round">
                <circle cx="12" cy="12" r="10"/>
                <polyline points="12 6 12 12 16 14"/>
            </svg>
        </div>
        <h1 class="ecom-title">Página expirada</h1>
        <p class="ecom-subtitle">
            Sua sessão expirou por inatividade. Por favor, faça login novamente.
        </p>
    </div>

<#elseif section = "form">

    <div class="ecom-actions">
        <a href="${url.loginUrl}" class="ecom-button">
            Fazer login novamente
        </a>
    </div>

</#if>

</@layout.registrationLayout>