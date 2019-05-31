#!/usr/bin/env bash

kubectl config set-cluster fuchicorp-cluster --server=https://kubernetes.default --certificate-authority=/var/run/secrets/kubernetes.io/serviceaccount/ca.crt  >> /var/log/kube-set-config.log
kubectl config set-context fuchicorp-context --cluster=fuchicorp-cluster >> /var/log/kube-set-config.log
kubectl config set-credentials fuchicorp-user --token="$(cat /var/run/secrets/kubernetes.io/serviceaccount/token)" >> /var/log/kube-set-config.log
kubectl config set-context fuchicorp-context --user=fuchicorp-user >> /var/log/kube-set-config.log
kubectl config use-context fuchicorp-context >> /var/log/kube-set-config.log
