package com.glowandrise.glowandrise_backend.service;

import com.glowandrise.glowandrise_backend.request.ContactBrand;
import com.glowandrise.glowandrise_backend.request.ContactInfluencer;

public interface ContactService {

    int contactInfluencer(ContactInfluencer influencer);

    int contactBrand(ContactBrand brand);
}