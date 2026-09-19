<#import "template.ftl" as layout>

<@layout.registrationLayout displayMessage=false ; section>

<#if section = "header">

    <div class="ecom-header">
        <div class="ecom-icon-circle" aria-hidden="true">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round">
                <path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"/>
                <polyline points="22,6 12,13 2,6"/>
            </svg>
        </div>
        <h1 class="ecom-title">Verifique seu e-mail</h1>
        <p class="ecom-subtitle">
            Enviamos um link de confirmação para o seu e-mail.
            Clique no link para ativar sua conta.
        </p>
    </div>

<#elseif section = "form">

    <div class="ecom-info">
        Não recebeu? Verifique a caixa de spam ou tente novamente em alguns minutos.
    </div>

    <div class="ecom-actions">
        <a href="${url.loginUrl}" class="ecom-button">
            Voltar ao login
        </a>
    </div>

</#if>

</@layout.registrationLayout>